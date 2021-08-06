package com.gshot.step.data.dao

import androidx.room.*
import com.gshot.step.data.entity.Cart
import com.gshot.step.data.entity.CartProductAssociation
import com.gshot.step.data.entity.relation.CartProductRelation

@Dao
 interface CartDao {

    @Insert
    fun addCart(cart: Cart): Long

    @Query("SELECT * FROM carts WHERE cart_id=:cartId")
    fun getCartWithId(cartId: Long): Cart

    @Query("SELECT * FROM carts WHERE user_id=:userId")
    fun getCartWithUserId(userId: Long): Cart?

    @Transaction
    @Query("SELECT * FROM carts")
    fun getProducts(): List<CartProductRelation>

    @Query("SELECT * FROM cartProductAssociation WHERE product_id=:productId")
    fun isProductInCart(productId: Long): CartProductAssociation?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProductToCart(cartProductAssociation: CartProductAssociation): Long

    @Transaction
    @Query("UPDATE cartProductAssociation SET qty=:qty WHERE cart_id=:cartId")
    fun updateProductQuantity(qty: Int, cartId: Int)

    @Transaction
    @Delete
    fun removeProductFromCart(cartProductAssociation: CartProductAssociation): Int
}