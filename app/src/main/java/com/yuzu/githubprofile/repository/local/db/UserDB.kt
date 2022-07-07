package com.yuzu.githubprofile.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yuzu.githubprofile.repository.data.UserData

/**
 * Created by Yustar Pramudana on 19/02/2021
 */

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class UserDB: RoomDatabase() {
    abstract fun userDAO(): UserDAO
}