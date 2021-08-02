package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.gshot.step.database.AppDatabase
import com.gshot.step.data.Repository

class CategoryFragmentViewModel(application: Application): AndroidViewModel(application) {

    //var _categories: MutableLiveData<List<Category>> = MutableLiveData()
    //var categories: LiveData<List<Category>> = _categories
    val database = AppDatabase.getInstance(application.applicationContext)
    val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getFavoriteDao())

    //init {
        //viewModelScope.launch(Dispatchers.IO) {
        //    _categories.postValue(repo.getCategories().value)
        //}
    //}

    fun getAll() = repo.getCategories()
}