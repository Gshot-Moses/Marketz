package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.gshot.step.data.Repository
import com.gshot.step.data.database.AppDatabase
import com.gshot.step.domain.service.CategoryService
import com.gshot.step.domain.service.ProductService
import com.gshot.step.mapper.entitydomainmapper.CategoryEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper
import com.gshot.step.mapper.domainviewmapper.CategoryDomainViewMapper
import com.gshot.step.mapper.domainviewmapper.ProductDomainViewMapper
import com.gshot.step.presentation.model.Category
import com.gshot.step.presentation.model.Product

class AddProductViewModel(application: Application): AndroidViewModel(application) {

    val database = AppDatabase.getInstance(application)
    val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getUserDao())
    val productService = ProductService(repo, ProductEntityModelMapper())
    val categoryService = CategoryService(repo, ProductEntityModelMapper(), CategoryEntityModelMapper())
    val categoryMapper = CategoryDomainViewMapper()
    val productMapper = ProductDomainViewMapper()

    val map: MutableMap<Int, String> = mutableMapOf()

    fun addProduct(product: Product): LiveData<Long> {
        return productService.addProduct(productMapper.fromModelToEntity(product))
    }

    fun populateMap(items: List<Category>) {
        items.forEach { category ->  map[category.id] = category.name }
    }

    fun filterMap(selection: String): List<Category> {
        return map.filter { it.value == selection }.map { Category(it.key, it.value) }
    }

    fun getAllCategories(): LiveData<List<Category>> {
        return categoryService.getCategories().map { categoryMapper.fromModelListToEntityList(it!!) }
    }
}