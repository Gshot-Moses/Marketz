package com.gshot.step.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.gshot.step.dao.CartDao
import com.gshot.step.dao.CategoryDao
import com.gshot.step.dao.FavoriteDao
import com.gshot.step.model.CartProductAssociation
import com.gshot.step.model.Category
import com.gshot.step.model.FavoriteProductAssociation
import com.gshot.step.model.Product

class Repository(val categoryDao: CategoryDao, val cartDao: CartDao, val favoriteDao: FavoriteDao) {

    fun getCategories(): LiveData<List<Category>> {
        return categoryDao.getCategories()
    }

    fun getProductsFromCategory(categoryId: Int): LiveData<List<Product>> {
        return categoryDao.getProducts(categoryId)
    }

    suspend fun addProductToCart(productId: Long, cartId: Long, qty: Int): LiveData<Long> = liveData {
        val association = CartProductAssociation(cartId, productId, qty)
        emit(cartDao.addProductToCart(association))
    }

    suspend fun removeProductFromCart(productId: Long, cartId: Long, qty: Int): LiveData<Int> = liveData {
        val association = CartProductAssociation(cartId, productId, qty)
        emit(cartDao.removeProductFromCart(association))
    }

    fun getProductsFromCart(cartId: Long): LiveData<List<Product>> {
        return cartDao.getProducts(cartId)
    }

    suspend fun addProductToFavorites(productId: Long, favoriteId: Long): LiveData<Long> = liveData {
        val association = FavoriteProductAssociation(favoriteId, productId)
        emit(favoriteDao.addProduct(association))
    }

    suspend fun removeProductFromFavorites(productId: Long, favoriteId: Long): LiveData<Int> = liveData {
        val association = FavoriteProductAssociation(favoriteId, productId)
        emit(favoriteDao.removeProduct(association))
    }

    fun getProductsFromFavorites(favoriteId: Long): LiveData<List<Product>> {
        return favoriteDao.getProducts(favoriteId).map { it.products }
    }
}