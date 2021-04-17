package com.online.test.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.online.test.data.Category
import com.online.test.data.ListRepository

class ListViewModel(application: Application): AndroidViewModel(application) {
    private val repo : ListRepository = ListRepository(application)

    val items: LiveData<List<Category>>
        get() = repo.getData()

}