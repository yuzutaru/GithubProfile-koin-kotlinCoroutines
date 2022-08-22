package com.yuzu.githubprofile.repository.data

/**
 * Created by Yustar Pramudana on 22/08/22.
 */

class Response<S, E: ErrorResponse.ErrorEntryWithMessage> {
    var success: Boolean = true
    var successData: S? = null
    var errorData: ErrorResponse<E>? = null

    inline fun onSuccess(block: (S) -> Unit) {
        if (success) {
            block(successData!!)
        }
    }

    inline fun onError(block: (ErrorResponse<E>) -> Unit) {
        if (!success) {
            block(errorData!!)
        }
    }
}