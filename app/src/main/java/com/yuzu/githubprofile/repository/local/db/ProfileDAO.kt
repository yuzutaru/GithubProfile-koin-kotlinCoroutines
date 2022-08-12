package com.yuzu.githubprofile.repository.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzu.githubprofile.repository.data.ProfileData

/**
 * Created by Yustar Pramudana on 24/07/2022
 */

@Dao
interface ProfileDAO {
    @Query("SELECT * from ProfileData")
    fun getAllProfiles(): Result<List<ProfileData>>

    @Query("SELECT * FROM ProfileData WHERE login = :login")
    fun getProfile(login: String): Result<ProfileData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profileData: ProfileData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profileDataList: List<ProfileData>)
}