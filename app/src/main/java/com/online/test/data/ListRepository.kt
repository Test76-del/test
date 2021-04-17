package com.online.test.data

import android.app.Application
import androidx.lifecycle.LiveData

class ListRepository(context:Application) {
    private val favouritesListDao: ListDao = ItemDatabase.getDatabase(context).favouritesListDao()

    fun getData(): LiveData<List<Category>> {
        return favouritesListDao.getData()
    }

}