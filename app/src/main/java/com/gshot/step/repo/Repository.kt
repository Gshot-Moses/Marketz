package com.gshot.step.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.gshot.step.dao.CartDao
import com.gshot.step.dao.CategoryDao
import com.gshot.step.dao.FavoriteDao
import com.gshot.step.model.CartProductAssociation
import com.gshot.step.model.Category
import com.gshot.step.model.FavoriteProductAssociation
import com.gshot.step.model.Product

class Repository(val categoryDao: CategoryDao, val cartDao: CartDao, val favoriteDao: FavoriteDao) {

    suspend fun getCategories(): LiveData<List<Category>> {
        return categoryDao.getCategories()
    }

    suspend fun getProductsFromCategory(categoryId: Int): LiveData<List<Product>> {
        return categoryDao.getProducts(categoryId).map { it.products }
    }

    suspend fun addProductToCart(productId: Long, cartId: Long): LiveData<Long> {
        val association = CartProductAssociation(cartId, productId)
        return cartDao.addProductToCart(association)
    }

    suspend fun removeProductFromCart(productId: Long, cartId: Long): LiveData<Long> {
        val association = CartProductAssociation(cartId, productId)
        return cartDao.removeProductFromCart(association)
    }

    suspend fun getProductsFromCart(cartId: Long): LiveData<List<Product>> {
        return cartDao.getProducts(cartId).map { it.products }
    }

    suspend fun addProductToFavorites(productId: Long, favoriteId: Long): LiveData<Long> {
        val association = FavoriteProductAssociation(favoriteId, productId)
        return favoriteDao.addProduct(association)
    }

    suspend fun removeProductFromFavorites(productId: Long, favoriteId: Long): LiveData<Long> {
        val association = FavoriteProductAssociation(favoriteId, productId)
        return favoriteDao.removeProduct(association)
    }

    suspend fun getProductsFromFavorites(favoriteId: Long): LiveData<List<Product>> {
        return favoriteDao.getProducts(favoriteId).map { it.products }
    }
}