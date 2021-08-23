package com.yuzu.githubprofile.model.network.repository

import com.yuzu.githubprofile.model.data.UserData
import com.yuzu.githubprofile.model.network.db.UserDAO
import io.reactivex.Single
import java.util.concurrent.Executor

/**
 * Created by Yustar Pramudana on 19/02/2021
 */

class UserDBRepositoryImpl(private val dao: UserDAO, private val exec: Executor): UserDBRepository {
    override fun getUserBySinceId(since: Int): Single<List<UserData>> {
        return dao.getUserBySinceId(since)
    }
    override fun getUsersBySearch(search: String): Single<List<UserData>> {
        return dao.getUsersBySearch(search)
    }

    override fun insert(userData: UserData) {
        exec.execute {
            dao.insert(userData)
        }
    }

    override fun insert(userDataList: List<UserData>) {
        exec.execute {
            dao.insert(userDataList)
        }
    }

    override fun updateNotes(id: Int, notes: String) {
        exec.execute {
            dao.updateNotes(id, notes)
        }
    }
}