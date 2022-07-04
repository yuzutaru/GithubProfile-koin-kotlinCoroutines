package com.yuzu.githubprofile.injection.module

import android.app.Application
import androidx.room.Room
import com.yuzu.githubprofile.model.network.db.UserDAO
import com.yuzu.githubprofile.model.network.db.UserDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): UserDB {
       return Room.databaseBuilder(application, UserDB::class.java, "user-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideUserDao(database: UserDB): UserDAO {
        return  database.userDAO()
    }

    single { provideDatabase(androidApplication()) }
    single {
        provideUserDao(get())
    }
}
