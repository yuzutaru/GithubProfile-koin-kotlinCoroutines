package com.yuzu.githubprofile.repository.remote

import com.yuzu.githubprofile.repository.data.ErrorResponse
import com.yuzu.githubprofile.repository.data.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit):
            CallAdapter<*, *>? {
        // Kotlin suspend function will wrap result with retrofit.Call
        if (Call::class.java != getRawType(returnType)) return null

        if (returnType !is ParameterizedType) {
            return null
        }

        val responseType = getParameterUpperBound(0, returnType)
        val responseRawType = getRawType(responseType)
        if (!retrofit2.Response::class.java.isAssignableFrom(responseRawType)) {
            return null
        }

        if (responseType !is ParameterizedType) return null

        @Suppress("UNCHECKED_CAST")
        val responseInstance =
            getRawType(responseType).newInstance() as Response<*, ErrorResponse.ErrorEntryWithMessage>
        val successType = getParameterUpperBound(0, responseType)
        val errorType =
            if (responseRawType == retrofit2.Response::class.java) {
                fetchErrorTypeFromResponse(responseType)
            } else {
                fetchErrorType(responseType) ?: return null
            }

        if (!ErrorResponse.ErrorEntryWithMessage::class.java.isAssignableFrom(getRawType(errorType))) {
            return null
        }

        val errorBodyConverter: Converter<ResponseBody, ErrorResponse.ErrorEntryWithMessage> =
            retrofit.nextResponseBodyConverter(null, errorType, annotations)

        return ResponseCallAdapter(
            responseInstance,
            successType,
            errorBodyConverter
        )
    }

    private fun fetchErrorTypeFromResponse(responseType: ParameterizedType): Type {
        return getParameterUpperBound(1, responseType)
    }

    private fun fetchErrorType(responseType: Type): Type? {
        return try {
            val superClass = responseType.getResponseType() as? ParameterizedType ?: return null
            val errorType = getRawType(getParameterUpperBound(1, superClass))
            if (ErrorResponse.ErrorEntryWithMessage::class.java.isAssignableFrom(errorType)) {
                errorType
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun Type.getResponseType(): Type? {
        if (retrofit2.Response::class.java == getRawType(this)) return this
        val supperClass =
            try {
                getRawType(this).genericSuperclass
            } catch (e: IllegalArgumentException) {
                null
            }
        if (supperClass != null) {
            return supperClass.getResponseType()
        }
        return null
    }
}

class ResponseCallAdapter<S, E : ErrorResponse.ErrorEntryWithMessage>(
    private val responseInstance: Response<S, E>,
    private val successType: Type,
    private val errorConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<Response<S, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<Response<S, E>> {
        return ResponseCall(call, responseInstance, errorConverter)
    }
}