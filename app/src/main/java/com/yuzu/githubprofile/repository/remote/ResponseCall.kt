package com.yuzu.githubprofile.repository.remote

import com.yuzu.githubprofile.repository.data.ErrorResponse
import com.yuzu.githubprofile.repository.data.Response
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter

class ResponseCall<S, E : ErrorResponse.ErrorEntryWithMessage>(
    private val delegate: Call<S>,
    private val responseInstance: Response<S, E>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<Response<S, E>> {

    override fun enqueue(callback: Callback<Response<S, E>>) {
        return delegate.enqueue(object : Callback<S> {

            override fun onResponse(call: Call<S>, response: retrofit2.Response<S>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    responseInstance.success = true
                    responseInstance.successData = body
                } else {
                    val error = response.errorBody()
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> {
                            try {
                                errorConverter.convert(error)
                            } catch (e: Exception) {
                                null
                            }
                        }
                    }
                    responseInstance.success = false
                    responseInstance.errorData = ErrorResponse.ApiError(errorBody, response.code())
                }
                callback.onResponse(this@ResponseCall, retrofit2.Response.success(responseInstance))
            }

            override fun onFailure(p0: Call<S>, p1: Throwable) {
                responseInstance.success = false
                responseInstance.errorData = ErrorResponse.NetworkError(p1)
                callback.onResponse(this@ResponseCall, retrofit2.Response.success(responseInstance))
            }
        })
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun clone(): Call<Response<S, E>> {
        return ResponseCall(delegate.clone(), responseInstance, errorConverter)
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun execute(): retrofit2.Response<Response<S, E>> {
        throw UnsupportedOperationException("ResponseCall not support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}