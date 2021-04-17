package com.online.test.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class TitleData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val category: String
)