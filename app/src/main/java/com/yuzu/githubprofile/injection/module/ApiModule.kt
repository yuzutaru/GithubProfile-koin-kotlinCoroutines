package com.yuzu.githubprofile.injection.module

import com.yuzu.githubprofile.model.network.api.ProfileApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideProfileApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }

    single { provideProfileApi(get()) }
}