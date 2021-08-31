package com.yuzu.githubprofile.model.network.repository

import android.content.Context
import android.os.Build
import android.util.Log
import com.yuzu.githubprofile.model.data.UserData
import com.yuzu.githubprofile.model.network.api.ProfileApi
import com.yuzu.githubprofile.model.network.db.UserDAO
import com.yuzu.githubprofile.util.AppResult
import com.yuzu.githubprofile.util.NetworkManager.isOnline
import com.yuzu.githubprofile.util.TAG
import com.yuzu.githubprofile.util.Utils.handleApiError
import com.yuzu.githubprofile.util.Utils.handleSuccess
import com.yuzu.githubprofile.util.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Yustar Pramudana on 18/02/2021
 */

class ProfileRepositoryImpl(
    private val api: ProfileApi,
    private val context: Context,
    private val dao: UserDAO
) :
    ProfileRepository {

    override suspend fun userList(since: Int): AppResult<List<UserData>> {
        if (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                isOnline(context)
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        ) {
            return try {
                val response = api.userList(since)
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) { dao.insert(it) }
                    }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {
            //check in db if the data exists
            val data = userListDB(since)
            return if (data.isNotEmpty()) {
                Log.d(TAG, "from db")
                AppResult.Success(data)
            } else
            //no network
                context.noNetworkConnectivityError()
        }
    }

    private suspend fun userListDB(since: Int): List<UserData> {
        return withContext(Dispatchers.IO) {
            dao.getUserBySinceId(since)
        }
    }
}