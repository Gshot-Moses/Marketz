package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gshot.step.database.AppDatabase
import com.gshot.step.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application): AndroidViewModel(application) {

    val database = AppDatabase.getInstance(application)
    val dao = database.getCategoryDao()

    fun addCategory(category: Category): LiveData<Long> {
        //viewModelScope.launch(Dispatchers.IO) {
        //    dao.addCategory(category)
        //}
        val result = dao.addCategory(category)
        val add :LiveData<Long> = MutableLiveData(result)
        return add
    }
}