package com.gshot.step.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gshot.step.data.entity.Category
import com.gshot.step.data.entity.Product

@Dao
interface CategoryDao {

    @Insert
    fun addCategory(category: Category): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProduct(product: Product): Long

    @Insert
    suspend fun addCategories(categories: List<Category>)

    @Delete
    suspend fun removeCategory(category: Category): Int

    @Query("SELECT category_id FROM category WHERE name=:name")
    fun getCategoryWithName(name: String): Long

    @Query("SELECT * FROM category WHERE category_id=:categoryId")
    fun getCategoryWithId(categoryId: Long): Category

    @Query("SELECT * FROM category")
    fun getCategories(): LiveData<List<Category>>

    @Transaction
    @Query("SELECT * FROM products WHERE category_id=:categoryId")
    fun getProducts(categoryId: Int): LiveData<List<Product>>
}