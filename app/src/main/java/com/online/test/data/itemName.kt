package com.online.test.data

import androidx.room.ColumnInfo

data class itemName(
    @ColumnInfo(name = "name")
    val name: String
)
