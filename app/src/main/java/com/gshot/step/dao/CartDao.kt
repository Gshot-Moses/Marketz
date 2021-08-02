package com.gshot.step.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gshot.step.model.Cart
import com.gshot.step.model.CartProductAssociation
import com.gshot.step.model.Product
import com.gshot.step.model.relation.CartProductRelation

@Dao
abstract class CartDao {

    @Insert
    abstract fun addCart(cart: Cart): Long

    @Query("SELECT * FROM carts WHERE user_id=:userId")
    abstract fun getCartWithUserId(userId: Long): Cart?

    @Transaction
    @Query("SELECT * FROM carts")
    abstract fun getProducts(): LiveData<List<CartProductRelation>>

    @Query("SELECT * FROM cartProductAssociation WHERE product_id=:productId")
    abstract fun isProductInCart(productId: Long): CartProductAssociation?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addProductToCart(cartProductAssociation: CartProductAssociation): Long

    @Transaction
    @Query("UPDATE cartProductAssociation SET qty=:qty WHERE cart_id=:cartId")
    abstract fun updateProductQuantity(qty: Int, cartId: Int)

    @Transaction
    @Delete
    abstract fun removeProductFromCart(cartProductAssociation: CartProductAssociation): Int
}