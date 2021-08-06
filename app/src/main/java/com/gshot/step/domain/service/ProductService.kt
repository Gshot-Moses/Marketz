package com.gshot.step.domain.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gshot.step.data.Repository
import com.gshot.step.domain.model.Product
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper

class ProductService(private val repo: Repository, private val mapper: ProductEntityModelMapper) {

    fun addProduct(product: Product): LiveData<Long> {
        return MutableLiveData(repo.addProduct(mapper.fromModelToEntity(product)))
    }

    fun searchProductByName(): List<Product>? {
        return null
    }
}