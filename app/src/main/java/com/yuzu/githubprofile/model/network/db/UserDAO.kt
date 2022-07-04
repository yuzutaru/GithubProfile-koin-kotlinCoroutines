package com.yuzu.githubprofile.model.network.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzu.githubprofile.model.data.UserData

/**
 * Created by Yustar Pramudana on 19/02/2021
 */

@Dao
interface UserDAO {
    @Query("SELECT * from UserData WHERE sinceId = :since")
    fun getUserBySinceId(since: Int): LiveData<List<UserData>>

    @Query("SELECT * FROM UserData WHERE UserData.login like :search||'%' OR UserData.notes like :search||'%'")
    fun getUsersBySearch(search: String): LiveData<List<UserData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userData: UserData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDataList: List<UserData>)

    @Query("UPDATE UserData SET notes = :notes WHERE id = :id")
    fun updateNotes(id: Int, notes: String)
}