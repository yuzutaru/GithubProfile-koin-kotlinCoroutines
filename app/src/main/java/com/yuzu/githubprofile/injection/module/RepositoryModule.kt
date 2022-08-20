package com.yuzu.githubprofile.injection.module

import com.yuzu.githubprofile.repository.local.contract.ProfileDBRepository
import com.yuzu.githubprofile.repository.local.contract.ProfileDBRepositoryImpl
import com.yuzu.githubprofile.repository.local.db.ProfileDAO
import com.yuzu.githubprofile.repository.remote.api.ProfileApi
import com.yuzu.githubprofile.repository.remote.contract.ProfileRepository
import com.yuzu.githubprofile.repository.remote.contract.ProfileRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    fun provideProfileRepository(api: ProfileApi): ProfileRepository {
        return ProfileRepositoryImpl(api)
    }

    fun provideProfileDBRepository(dao: ProfileDAO): ProfileDBRepository {
        return ProfileDBRepositoryImpl(dao)
    }

    single { provideProfileRepository(get()) }
    single { provideProfileDBRepository(get()) }
}