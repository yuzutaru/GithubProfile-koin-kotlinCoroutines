package com.yuzu.githubprofile.repository.remote.api

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.UserData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Yustar Pramudana on 18/02/2021
 */

interface ProfileApi {

    /**
     * User List
     * */
    @GET(value = "users")
    suspend fun userList(@Query("since") since: Int): ProfileResponse<List<UserData>>

    /**
     * User Detail
     * */
    @GET(value = "users/{username}")
    suspend fun userDetail(@Path(value = "username") username: String): ProfileResponse<ProfileData>
}