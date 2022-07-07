package com.yuzu.githubprofile.injection.module

import android.content.Context
import com.yuzu.githubprofile.repository.remote.api.ProfileApi
import com.yuzu.githubprofile.repository.local.db.UserDAO
import com.yuzu.githubprofile.repository.remote.contract.ProfileRepository
import com.yuzu.githubprofile.repository.remote.contract.ProfileRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {

    fun provideProfileRepository(api: ProfileApi): ProfileRepository {
        return ProfileRepositoryImpl(api)
    }

    single { provideProfileRepository(get<Retrofit>().create(ProfileApi::class.java)) }
}