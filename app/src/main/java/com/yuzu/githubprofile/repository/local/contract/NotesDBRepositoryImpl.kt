package com.yuzu.githubprofile.repository.local.contract

import com.yuzu.githubprofile.repository.data.NotesData
import com.yuzu.githubprofile.repository.local.local.NotesDAO
import io.reactivex.Single
import java.util.concurrent.Executor

/**
 * Created by Yustar Pramudana on 25/02/2021
 */

class NotesDBRepositoryImpl(private val dao: NotesDAO, private val exec: Executor): NotesDBRepository {
    override fun getAll(): Single<List<NotesData>> {
        return dao.getAll()
    }

    override fun get(login: String): Single<NotesData> {
        return dao.get(login)
    }

    override fun insert(data: NotesData) {
        exec.execute {
            dao.insert(data)
        }
    }

    override fun insert(list: List<NotesData>) {
        exec.execute {
            dao.insert(list)
        }
    }
}