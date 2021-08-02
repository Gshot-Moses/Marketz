package com.gshot.step.domain

import android.content.Context
import com.gshot.step.Utils
import com.gshot.step.dao.CartDao
import com.gshot.step.database.AppDatabase
import com.gshot.step.model.Cart
import com.gshot.step.model.CartProductAssociation
import com.gshot.step.model.Product

class CartService private constructor(context: Context){

    val cartDao: CartDao

    init {
        cartDao = AppDatabase.getInstance(context).getCartDao()
    }

    fun checkCart(userId: Long): Boolean {
        val cart = cartDao.getCartWithUserId(userId)
        return cart != null
    }

    fun getCart(userId: Long): Cart {
        return cartDao.getCartWithUserId(userId)!!
    }

    fun createCart(userId: Long): Cart {
        cartDao.addCart(Cart(0, userId))
        return cartDao.getCartWithUserId(userId)!!
    }

    fun addProductToCart(product: Product, qty: Int, userId: Long) {
        val cart = cartDao.getCartWithUserId(userId)
        cartDao.addProductToCart(CartProductAssociation(cart!!.id!!, product.id, qty))
    }

    fun updateProductQty(qty: Int, cartId: Int) {
        cartDao.updateProductQuantity(qty, cartId)
    }

    fun isProductInCart(product: Product): Boolean {
        return cartDao.isProductInCart(product.id) != null
    }

    fun removeProductFromCart(product: Product) {
        cartDao.removeProductFromCart(CartProductAssociation(Utils.cart!!.id!!, product.id, 1))
    }

    companion object {
        var INSTANCE: CartService? = null

        fun getInstance(context: Context): CartService {
            return INSTANCE ?: synchronized(this) {
                CartService(context).also { INSTANCE = it }
            }
        }
    }
}