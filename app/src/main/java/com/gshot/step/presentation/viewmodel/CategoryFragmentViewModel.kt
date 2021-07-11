package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gshot.step.database.AppDatabase
import com.gshot.step.model.Category
import com.gshot.step.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryFragmentViewModel(application: Application): AndroidViewModel(application) {

    lateinit var categories: LiveData<List<Category>>
    val database = AppDatabase.getInstance(application.applicationContext)
    val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getFavoriteDao())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            categories = repo.getCategories()
        }
    }
}