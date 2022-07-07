package com.yuzu.githubprofile.injection.module

import com.yuzu.githubprofile.repository.data.ResponseHandler
import com.yuzu.githubprofile.repository.remote.api.ProfileApi
import com.yuzu.githubprofile.repository.remote.contract.ProfileRepository
import com.yuzu.githubprofile.repository.remote.contract.ProfileRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {

    fun provideProfileRepository(api: ProfileApi, responseHandler: ResponseHandler): ProfileRepository {
        return ProfileRepositoryImpl(api, responseHandler)
    }

    single { provideProfileRepository(get<Retrofit>().create(ProfileApi::class.java), get()) }
}