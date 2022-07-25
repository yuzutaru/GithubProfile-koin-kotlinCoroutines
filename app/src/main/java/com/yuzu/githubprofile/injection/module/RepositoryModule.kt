package com.yuzu.githubprofile.injection.module

import com.yuzu.githubprofile.repository.data.ResponseHandler
import com.yuzu.githubprofile.repository.model.contract.ProfileDBRepository
import com.yuzu.githubprofile.repository.model.contract.ProfileDBRepositoryImpl
import com.yuzu.githubprofile.repository.model.local.ProfileDAO
import com.yuzu.githubprofile.repository.remote.api.ProfileApi
import com.yuzu.githubprofile.repository.remote.contract.ProfileRepository
import com.yuzu.githubprofile.repository.remote.contract.ProfileRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {

    fun provideProfileRepository(api: ProfileApi, responseHandler: ResponseHandler): ProfileRepository {
        return ProfileRepositoryImpl(api, responseHandler)
    }

    fun provideProfileDBRepository(dao: ProfileDAO): ProfileDBRepository {
        return ProfileDBRepositoryImpl(dao)
    }

    single { provideProfileRepository(get(), get()) }
    single { provideProfileDBRepository(get()) }
}