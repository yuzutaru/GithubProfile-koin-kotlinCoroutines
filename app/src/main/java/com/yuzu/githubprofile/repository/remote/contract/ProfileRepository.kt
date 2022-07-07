package com.yuzu.githubprofile.repository.remote.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.Resource
import com.yuzu.githubprofile.repository.data.UserData

/**
 * Created by Yustar Pramudana on 18/02/2021
 */

interface ProfileRepository {
    fun userList(since: Int): Resource<List<UserData>>
    fun userDetail(username: String): Resource<ProfileData>
}