package com.gshot.step.dao

import androidx.room.Insert
import com.gshot.step.model.Product

interface ProductDao {

    @Insert
    suspend fun addProduct(product: Product): Long

    @Insert
    suspend fun addProducts(products: List<Product>)
}