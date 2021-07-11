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
    suspend fun removeFavorite(favorite: Favorite): Long

    @Transaction
    @Insert
    suspend fun addProduct(favoriteProductAssociation: FavoriteProductAssociation): LiveData<Long>

    @Transaction
    @Delete
    suspend fun removeProduct(favoriteProductAssociation: FavoriteProductAssociation): LiveData<Long>

    @Transaction
    @Query("SELECT * FROM favoriteProductAssociation WHERE favorite_id=:favoriteId")
    suspend fun getProducts(favoriteId: Long): LiveData<FavoriteProductRelation>
}