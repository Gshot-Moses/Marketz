package com.gshot.step.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gshot.step.data.entity.Favorite
import com.gshot.step.data.entity.FavoriteProductAssociation
import com.gshot.step.data.entity.relation.FavoriteProductRelation

@Dao
interface FavoriteDao {
    @Insert
    suspend fun addFavorite(favorite: Favorite): Long

    @Delete
    suspend fun removeFavorite(favorite: Favorite): Int

    @Transaction
    @Insert
    fun addProduct(favoriteProductAssociation: FavoriteProductAssociation): Long

    @Transaction
    @Delete
    fun removeProduct(favoriteProductAssociation: FavoriteProductAssociation): Int

    @Transaction
    @Query("SELECT * FROM favoriteProductAssociation WHERE favorite_id=:favoriteId")
    fun getProducts(favoriteId: Long): LiveData<FavoriteProductRelation>
}