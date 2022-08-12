package com.yuzu.githubprofile.repository.model.contract

import com.yuzu.githubprofile.repository.data.ProfileData

/**
 * Created by Yustar Pramudana on 21/02/2021
 */

interface ProfileDBRepository {
    fun getAllProfiles(): Result<List<ProfileData>>
    fun getProfile(login: String): Result<ProfileData>
    fun insert(profileData: ProfileData)
    fun insert(profileDataList: List<ProfileData>)
}