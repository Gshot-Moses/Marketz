package com.gshot.step.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gshot.step.model.Category
import com.gshot.step.model.Product

@Dao
interface CategoryDao {

    @Insert
    fun addCategory(category: Category): Long

    @Insert
    suspend fun addCategories(categories: List<Category>)

    @Delete
    suspend fun removeCategory(category: Category): Int

    @Query("SELECT * FROM category")
    fun getCategories(): LiveData<List<Category>>

    @Transaction
    @Query("SELECT * FROM products WHERE category_id=:categoryId")
    fun getProducts(categoryId: Int): LiveData<List<Product>>
}