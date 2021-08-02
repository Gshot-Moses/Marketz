package com.gshot.step.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gshot.step.model.Favorite
import com.gshot.step.model.FavoriteProductAssociation
import com.gshot.step.model.relation.FavoriteProductRelation

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