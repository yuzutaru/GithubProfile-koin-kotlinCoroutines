package com.yuzu.githubprofile.repository.model.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.model.local.ProfileDAO
import io.reactivex.Single
import java.util.concurrent.Executor

/**
 * Created by Yustar Pramudana on 21/02/2021
 */

class ProfileDBRepositoryImpl(private val dao: ProfileDAO, private val exec: Executor): ProfileDBRepository {
    override fun getAllProfiles(): Single<List<ProfileData>> {
        return dao.getAllProfiles()
    }

    override fun getProfile(login: String): Single<ProfileData> {
        return dao.getProfile(login)
    }

    override fun insert(profileData: ProfileData) {
        exec.execute {
            dao.insert(profileData)
        }
    }

    override fun insert(profileDataList: List<ProfileData>) {
        exec.execute {
            dao.insert(profileDataList)
        }
    }
}