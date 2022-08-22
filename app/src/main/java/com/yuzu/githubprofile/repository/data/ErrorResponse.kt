package com.yuzu.githubprofile.repository.data

import android.content.res.Resources
import com.yuzu.githubprofile.R

/**
 * Created by Yustar Pramudana on 22/08/22.
 */

sealed class ErrorResponse<T : ErrorResponse.ErrorEntryWithMessage>(val message: String) {
    data class ApiError<T : ErrorEntryWithMessage>(val data: T?, val code: Int) :
        ErrorResponse<T>(data?.errorMessage ?: Resources.getSystem().getString(R.string.unknown_error))

    data class NetworkError<T : ErrorEntryWithMessage>(val error: Throwable) :
        ErrorResponse<T>(Resources.getSystem().getString(R.string.network_error))

    interface ErrorEntryWithMessage {
        val errorMessage: String
    }
}