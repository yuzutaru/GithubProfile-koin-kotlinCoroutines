package com.yuzu.githubprofile.repository.remote.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.UserData
import com.yuzu.githubprofile.repository.remote.api.ProfileApi
import com.yuzu.githubprofile.repository.remote.api.ProfileResponse

/**
 * Created by Yustar Pramudana on 18/02/2021
 */

class ProfileRepositoryImpl(private val api: ProfileApi): ProfileRepository {
    override suspend fun userList(since: Int): ProfileResponse<List<UserData>> {
        return api.userList(since)
    }

    override suspend fun userDetail(username: String): ProfileResponse<ProfileData> {
        return api.userDetail(username)
    }
}