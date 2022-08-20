package com.yuzu.githubprofile.repository.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yuzu.githubprofile.repository.data.ProfileData

/**
 * Created by Yustar Pramudana on 24/07/2022
 */

@Database(entities = [ProfileData::class], version = 1, exportSchema = false)
abstract class ProfileDB: RoomDatabase() {
    abstract fun profileDAO(): ProfileDAO
}