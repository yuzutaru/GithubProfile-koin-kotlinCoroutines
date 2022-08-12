package com.yuzu.githubprofile.repository.remote.api

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.UserData
import retrofit2.http.*

/**
 * Created by Yustar Pramudana on 18/02/2021
 */

interface ProfileApi {

    /**
     * User List
     * */
    @GET(value = "users")
    fun userList(@Query("since") since: Int): Result<List<UserData>>

    /**
     * User Detail
     * */
    @GET(value = "users/{username}")
    fun userDetail(@Path(value = "username") username: String): Result<ProfileData>
}