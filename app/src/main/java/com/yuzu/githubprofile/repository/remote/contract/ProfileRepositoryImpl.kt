package com.yuzu.githubprofile.repository.remote.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.UserData
import com.yuzu.githubprofile.repository.model.Response
import com.yuzu.githubprofile.repository.remote.api.ProfileApi

/**
 * Created by Yustar Pramudana on 23/08/2022
 */

class ProfileRepositoryImpl(private val api: ProfileApi): ProfileRepository {
    override suspend fun userList(since: Int): Response<List<UserData>> {
        return api.userList(since)
    }

    override suspend fun userDetail(username: String): Response<ProfileData> {
        return api.userDetail(username)
    }
}