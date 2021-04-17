package com.online.test.ui

import android.app.Application
import androidx.lifecycle.*
import com.online.test.data.TitleData
import com.online.test.data.DetailRepository
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val repo: DetailRepository = DetailRepository(application)


    fun saveTask(data: TitleData){
        viewModelScope.launch {
                repo.insertTask(data)
        }
    }
}