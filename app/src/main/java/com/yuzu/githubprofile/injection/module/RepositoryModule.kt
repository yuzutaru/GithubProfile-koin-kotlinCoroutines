package com.yuzu.githubprofile.injection.module

import android.content.Context
import com.yuzu.githubprofile.model.network.api.ProfileApi
import com.yuzu.githubprofile.model.network.db.UserDAO
import com.yuzu.githubprofile.model.network.repository.ProfileRepository
import com.yuzu.githubprofile.model.network.repository.ProfileRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideProfileRepository(api: ProfileApi, context: Context, dao : UserDAO): ProfileRepository {
        return ProfileRepositoryImpl(api, context, dao)
    }

    single { provideProfileRepository(get(), androidContext(), get()) }
}