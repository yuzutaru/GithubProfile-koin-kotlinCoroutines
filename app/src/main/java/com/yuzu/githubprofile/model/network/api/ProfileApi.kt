package com.yuzu.githubprofile.model.network.api

import com.yuzu.githubprofile.model.data.UserData
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Yustar Pramudana on 18/02/2021
 */

interface ProfileApi {

    /**
     * User List
     * */
    @GET(value = "users")
    open fun userList(@Query("since") since: Int): Response<List<UserData>>

    /**
     * User Detail
     * */
    /*@GET(value = "users/{username}")
    open fun userDetail(@Path(value = "username") username: String): Single<ProfileData>*/
}