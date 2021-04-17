package com.online.test.data

import android.app.Application

class DetailRepository(context: Application) {
    private val favouritesDetailDao : FavouritesDetailDao = ItemDatabase.getDatabase(context).favouritesDetailDao()

    suspend fun insertTask(item: TitleData): Long {
        return favouritesDetailDao.insertData(item)
    }

}