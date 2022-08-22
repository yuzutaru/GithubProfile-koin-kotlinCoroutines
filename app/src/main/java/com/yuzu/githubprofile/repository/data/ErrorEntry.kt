package com.yuzu.githubprofile.repository.data

import android.content.res.Resources
import com.google.gson.annotations.SerializedName
import com.yuzu.githubprofile.R

/**
 * Created by Yustar Pramudana on 22/08/22.
 */

data class ErrorEntry(
    val message: String?,
    @SerializedName("documentation_url")
    val documentationUrl: String?

) : ErrorResponse.ErrorEntryWithMessage {

    override val errorMessage: String
        get() = message ?: Resources.getSystem().getString(R.string.unknown_error)
}
