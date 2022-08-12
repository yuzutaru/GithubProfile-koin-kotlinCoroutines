package com.yuzu.githubprofile.repository.remote.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.UserData
import com.yuzu.githubprofile.repository.remote.api.ProfileApi

/**
 * Created by Yustar Pramudana on 18/02/2021
 */

class ProfileRepositoryImpl(private val api: ProfileApi): ProfileRepository {
    override fun userList(since: Int): Result<List<UserData>> {
        return api.userList(since)
    }

    override fun userDetail(username: String): Result<ProfileData> {
        return api.userDetail(username)
    }
}