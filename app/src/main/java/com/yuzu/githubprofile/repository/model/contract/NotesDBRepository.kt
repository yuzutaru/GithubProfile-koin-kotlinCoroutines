package com.yuzu.githubprofile.repository.model.contract

import com.yuzu.githubprofile.repository.data.NotesData
import io.reactivex.Single

/**
 * Created by Yustar Pramudana on 25/02/2021
 */

interface NotesDBRepository {
    fun getAll(): Single<List<NotesData>>
    fun get(login: String): Single<NotesData>
    fun insert(data: NotesData)
    fun insert(list: List<NotesData>)
}