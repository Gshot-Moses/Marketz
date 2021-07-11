package com.gshot.step.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gshot.step.model.Cart
import com.gshot.step.model.CartProductAssociation
import com.gshot.step.model.relation.CartProductRelation

@Dao
interface CartDao {

    @Insert
    suspend fun addCart(cart: Cart): Long

    @Transaction
    @Query("SELECT * FROM cartProductAssociation WHERE cart_id=:cartId")
    suspend fun getProducts(cartId: Long): LiveData<CartProductRelation>

    @Transaction
    @Insert
    suspend fun addProductToCart(cartProductAssociation: CartProductAssociation): LiveData<Long>

    @Transaction
    @Delete
    suspend fun removeProductFromCart(cartProductAssociation: CartProductAssociation): LiveData<Long>
}