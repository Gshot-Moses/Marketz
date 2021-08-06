package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gshot.step.data.Repository
import com.gshot.step.data.database.AppDatabase
import com.gshot.step.domain.service.CategoryService
import com.gshot.step.mapper.entitydomainmapper.CategoryEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper
import com.gshot.step.mapper.domainviewmapper.ProductDomainViewMapper
import com.gshot.step.presentation.model.Product

class ProductFragmentViewModel(application: Application): AndroidViewModel(application) {

    val database = AppDatabase.getInstance(application)
    val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getUserDao())
    val categoryService = CategoryService(repo, ProductEntityModelMapper(), CategoryEntityModelMapper())
    val productMapper = ProductDomainViewMapper()
    var categoryId: Int = -1

    fun getProducts(categoryId: Int): LiveData<List<Product>?> {
        val result = categoryService.getProducts(categoryId).value
        val data: LiveData<List<Product>?> = MutableLiveData(result?.let { productMapper.fromDomainListToViewList(it) })
        return data
    }
}