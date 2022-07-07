package com.yuzu.githubprofile.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Yustar Pramudana on 25/02/2021
 */

@Entity
data class NotesData(
        @PrimaryKey
        var id: Int,
        var login: String? = null,
        var notes: String? = null
)
