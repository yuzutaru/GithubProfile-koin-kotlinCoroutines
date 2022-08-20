package com.yuzu.githubprofile.injection.module

import android.app.Application
import androidx.room.Room
import com.yuzu.githubprofile.repository.local.db.ProfileDAO
import com.yuzu.githubprofile.repository.local.db.ProfileDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Yustar Pramudana on 24/07/22.
 */

val dbModule = module {

    fun profileDB(app: Application): ProfileDB {
        return Room.databaseBuilder(app, ProfileDB::class.java, "profile.db").build()
    }

    fun provideProfileDao(profileDB: ProfileDB): ProfileDAO {
        return profileDB.profileDAO();
    }

    single { profileDB(androidApplication()) }
    single { provideProfileDao(get()) }
}