package com.gshot.step.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gshot.step.model.Category
import com.gshot.step.model.relation.CategoryProductRelation

@Dao
interface CategoryDao {

    @Insert
    suspend fun addCategory(category: Category): Long

    @Delete
    suspend fun removeCategory(category: Category): Long

    @Query("SELECT * FROM category")
    suspend fun getCategories(): LiveData<List<Category>>

    @Transaction
    @Query("SELECT * FROM products WHERE category_id=:categoryId")
    suspend fun getProducts(categoryId: Int): LiveData<CategoryProductRelation>
}