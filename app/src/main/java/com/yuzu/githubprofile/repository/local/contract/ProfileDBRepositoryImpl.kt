package com.yuzu.githubprofile.repository.model.contract

import androidx.lifecycle.LiveData
import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.Resource
import com.yuzu.githubprofile.repository.data.ResponseHandler
import com.yuzu.githubprofile.repository.model.local.ProfileDAO
/**
 * Created by Yustar Pramudana on 21/02/2021
 */

class ProfileDBRepositoryImpl(private val dao: ProfileDAO, private val responseHandler: ResponseHandler): ProfileDBRepository {
    override fun getAllProfiles(): Resource<List<ProfileData>> {
        return try {
            return responseHandler.handleSuccess(dao.getAllProfiles())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override fun getProfile(login: String): Resource<ProfileData> {
        return try {
            return responseHandler.handleSuccess(dao.getProfile(login))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override fun insert(profileData: ProfileData) {
        dao.insert(profileData)
    }

    override fun insert(profileDataList: List<ProfileData>) {
        dao.insert(profileDataList)
    }
}