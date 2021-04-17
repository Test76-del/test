package com.online.test.data

import androidx.room.*

@Dao
interface FavouritesDetailDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(task: TitleData): Long

}