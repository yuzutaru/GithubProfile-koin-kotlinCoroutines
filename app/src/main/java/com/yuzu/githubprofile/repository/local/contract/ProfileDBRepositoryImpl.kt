package com.yuzu.githubprofile.repository.model.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.model.local.ProfileDAO
/**
 * Created by Yustar Pramudana on 21/02/2021
 */

class ProfileDBRepositoryImpl(private val dao: ProfileDAO): ProfileDBRepository {
    override fun getAllProfiles(): Result<List<ProfileData>> {
        return dao.getAllProfiles()
    }

    override fun getProfile(login: String): Result<ProfileData> {
        return dao.getProfile(login)
    }

    override fun insert(profileData: ProfileData) {
        dao.insert(profileData)
    }

    override fun insert(profileDataList: List<ProfileData>) {
        dao.insert(profileDataList)
    }
}