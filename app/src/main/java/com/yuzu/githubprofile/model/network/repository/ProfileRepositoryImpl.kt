package com.yuzu.githubprofile.model.network.repository

import android.util.Log
import com.yuzu.githubprofile.model.data.UserData
import com.yuzu.githubprofile.model.network.api.ProfileApi
import com.yuzu.githubprofile.model.util.AppResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Yustar Pramudana on 18/02/2021
 */

/*
class ProfileRepositoryImpl(private val api: ProfileApi): ProfileRepository {
    */
/*override fun userList(since: Int): AppResult<List<UserData>> {
        return api.userList(since)
    }*//*


    */
/*override fun userDetail(username: String): Single<ProfileData> {
        return api.userDetail(username)
    }*//*


    override suspend fun userList(): AppResult<List<UserData>> {
        if (isOnline(context)) {
            return try {
                val response = api.getAllCountries()
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) { dao.add(it) }
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
            val data = getCountriesDataFromCache()
            return if (data.isNotEmpty()) {
                Log.d(TAG, "from db")
                AppResult.Success(data)
            } else
            //no network
                context.noNetworkConnectivityError()
        }
    }

    private suspend fun getCountriesDataFromCache(): List<CountriesData> {
        return withContext(Dispatchers.IO) {
            dao.findAll()
        }
    }
}*/
