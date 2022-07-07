package com.yuzu.githubprofile.repository.remote.contract

import android.content.Context
import androidx.lifecycle.LiveData
import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.Resource
import com.yuzu.githubprofile.repository.data.ResponseHandler
import com.yuzu.githubprofile.repository.data.UserData
import com.yuzu.githubprofile.repository.remote.api.ProfileApi
import com.yuzu.githubprofile.repository.local.db.UserDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Yustar Pramudana on 18/02/2021
 */

class ProfileRepositoryImpl(private val api: ProfileApi, private val responseHandler: ResponseHandler): ProfileRepository {
    override fun userList(since: Int): Resource<List<UserData>> {
        return try {
            return responseHandler.handleSuccess(api.userList(since))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override fun userDetail(username: String): Resource<ProfileData> {
        return try {
            return responseHandler.handleSuccess(api.userDetail(username))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}