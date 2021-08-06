package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.gshot.step.data.database.AppDatabase
import com.gshot.step.data.Repository
import com.gshot.step.domain.service.CategoryService
import com.gshot.step.mapper.entitydomainmapper.CategoryEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper
import com.gshot.step.mapper.domainviewmapper.CategoryDomainViewMapper
import com.gshot.step.presentation.model.Category

class CategoryFragmentViewModel(application: Application): AndroidViewModel(application) {

    //var _categories: MutableLiveData<List<Category>> = MutableLiveData()
    //var categories: LiveData<List<Category>> = _categories
    val database = AppDatabase.getInstance(application)
    val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getUserDao())
    val categoryService = CategoryService(repo, ProductEntityModelMapper(), CategoryEntityModelMapper())
    val categoryMapper = CategoryDomainViewMapper()

    //init {
        //viewModelScope.launch(Dispatchers.IO) {
        //    _categories.postValue(repo.getCategories().value)
        //}
    //}

    fun getAll(): LiveData<List<Category>?> {
        return categoryService.getCategories().map { it?.let { list -> categoryMapper.fromModelListToEntityList(list) } }
    }
}