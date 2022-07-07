package com.yuzu.githubprofile

import android.app.Application
import com.yuzu.githubprofile.injection.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Yustar Pramudana on 06/07/22.
 */

class GithubProfileApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GithubProfileApplication)
            modules(appModule)
        }
    }
}