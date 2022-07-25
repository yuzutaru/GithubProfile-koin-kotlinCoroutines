package com.yuzu.githubprofile.repository.model.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.Resource

/**
 * Created by Yustar Pramudana on 21/02/2021
 */

interface ProfileDBRepository {
    fun getAllProfiles(): Resource<List<ProfileData>>
    fun getProfile(login: String): Resource<ProfileData>
    fun insert(profileData: ProfileData)
    fun insert(profileDataList: List<ProfileData>)
}