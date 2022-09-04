package com.yuzu.githubprofile.repository.local.contract

import androidx.lifecycle.LiveData
import com.yuzu.githubprofile.repository.data.ProfileData

/**
 * Created by Yustar Pramudana on 21/02/2021
 */

interface ProfileDBRepository {
    fun getAllProfiles(): List<ProfileData>
    fun getProfile(login: String): ProfileData?
    fun insert(profileData: ProfileData)
    fun insert(profileDataList: List<ProfileData>)
}