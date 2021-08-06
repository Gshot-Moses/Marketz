package com.gshot.step.domain.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.gshot.step.data.Repository
import com.gshot.step.domain.model.Category
import com.gshot.step.domain.model.Product
import com.gshot.step.mapper.entitydomainmapper.CategoryEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper

class CategoryService(val repo: Repository, val productmapper: ProductEntityModelMapper, val categoryMapper: CategoryEntityModelMapper) {

    fun addCategory(category: Category): LiveData<Long> {
        val data = repo.addCategory(categoryMapper.fromModelToEntity(category))
        return MutableLiveData(data)
    }
    fun getProducts(categoryId: Int): LiveData<List<Product>?> {
        val products = repo.getProductsFromCategory(categoryId).value
        val category = repo.getCategoryWithId(categoryId.toLong())
        val models = products?.let { productmapper.fromEntityListToModelList(it) }
        models?.forEach { it.category.name = category.name }
        return MutableLiveData(models)
    }

    fun getCategories(): LiveData<List<Category>?> {
        return repo.getCategories().map { categoryMapper.fromEntityListToModelList(it) }
        //val categories = repo.getCategories().value
        //return MutableLiveData(categories?.let { categoryMapper.fromEntityListToModelList(it) })
    }
}