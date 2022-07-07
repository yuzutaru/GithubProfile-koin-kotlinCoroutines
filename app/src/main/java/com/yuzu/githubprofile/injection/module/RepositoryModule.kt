package com.yuzu.githubprofile.injection.module

import android.content.Context
import com.yuzu.githubprofile.repository.remote.api.ProfileApi
import com.yuzu.githubprofile.repository.model.db.UserDAO
import com.yuzu.githubprofile.repository.contract.ProfileRepository
import com.yuzu.githubprofile.repository.contract.ProfileRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {

    fun provideProfileRepository(api: ProfileApi, context: Context, dao : UserDAO): ProfileRepository {
        return ProfileRepositoryImpl(api, context, dao)
    }

    single { provideProfileRepository(get<Retrofit>().create(ProfileApi::class.java), androidContext(), get()) }
}