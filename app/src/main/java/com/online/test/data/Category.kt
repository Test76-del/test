package com.online.test.data

import androidx.room.ColumnInfo
import androidx.room.Relation


data class Category(
    @ColumnInfo(name = "category")
    var category: String,

    @Relation(parentColumn = "category", entityColumn = "category", entity = TitleData::class)
    val children : List<itemName>
)