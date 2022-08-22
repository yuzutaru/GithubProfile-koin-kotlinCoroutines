package com.yuzu.githubprofile.repository.remote.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.UserData
import com.yuzu.githubprofile.repository.remote.api.ProfileResponse

/**
 * Created by Yustar Pramudana on 18/02/2021
 */

interface ProfileRepository {
    suspend fun userList(since: Int): ProfileResponse<List<UserData>>
    suspend fun userDetail(username: String): ProfileResponse<ProfileData>
}