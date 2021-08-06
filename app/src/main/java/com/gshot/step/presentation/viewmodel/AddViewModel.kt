package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gshot.step.data.Repository
import com.gshot.step.data.database.AppDatabase
import com.gshot.step.domain.model.Category
import com.gshot.step.domain.service.CategoryService
import com.gshot.step.mapper.entitydomainmapper.CategoryEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper

class AddViewModel(application: Application): AndroidViewModel(application) {

    val database = AppDatabase.getInstance(application)
    val dao = database.getCategoryDao()
    val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getUserDao())
    val categoryService = CategoryService(repo, ProductEntityModelMapper(), CategoryEntityModelMapper())

    fun addCategory(category: Category): LiveData<Long> {
        //viewModelScope.launch(Dispatchers.IO) {
        //    dao.addCategory(category)
        //}
        return categoryService.addCategory(category)
    }
}