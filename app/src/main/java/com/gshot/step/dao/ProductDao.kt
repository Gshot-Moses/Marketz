package com.gshot.step.dao

import androidx.room.Dao
import androidx.room.Insert
import com.gshot.step.model.Product

@Dao
interface ProductDao {

    @Insert
    fun addProduct(product: Product): Long

    @Insert
    suspend fun addProducts(products: List<Product>)
}