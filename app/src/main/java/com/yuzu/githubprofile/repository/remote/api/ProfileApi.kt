package com.yuzu.githubprofile.repository.remote.api

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.UserData
import com.yuzu.githubprofile.repository.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Yustar Pramudana on 23/08/2022
 */

interface ProfileApi {

    /**
     * User List
     * */
    @GET(value = "users")
    suspend fun userList(@Query("since") since: Int): Response<List<UserData>>

    /**
     * User Detail
     * */
    @GET(value = "users/{username}")
    suspend fun userDetail(@Path(value = "username") username: String): Response<ProfileData>
}