package com.gshot.step.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.gshot.step.data.dao.CartDao
import com.gshot.step.data.dao.CategoryDao
import com.gshot.step.data.dao.FavoriteDao
import com.gshot.step.data.dao.UserDao
import com.gshot.step.data.entity.*
import com.gshot.step.data.entity.relation.CartProductRelation

class Repository(val categoryDao: CategoryDao, val cartDao: CartDao, val userDao: UserDao) {

    fun addCategory(category: Category) = categoryDao.addCategory(category)

    fun addProduct(product: Product) = categoryDao.addProduct(product)

    fun getCategories(): LiveData<List<Category>> {
        return categoryDao.getCategories()
    }

    fun getCategoryWithId(categoryId: Long) = categoryDao.getCategoryWithId(categoryId)

    fun getProductsFromCategory(categoryId: Int): LiveData<List<Product>> {
        return categoryDao.getProducts(categoryId)
    }

    fun createCart(userId: Long) {
        cartDao.addCart(Cart(0, userId))
    }

    fun getCartWithUserId(userId: Long) = cartDao.getCartWithUserId(userId)

    fun getCartWithId(cartId: Long) = cartDao.getCartWithId(cartId)

    fun addProductToCart(productId: Long, cartId: Long, qty: Int) {
        val association = CartProductAssociation(cartId, productId, qty)
        cartDao.addProductToCart(association)
    }

    fun updateProductQuantity(qty: Int, cartId: Int) {
        cartDao.updateProductQuantity(qty, cartId)
    }

    fun removeProductFromCart(productId: Long, cartId: Long, qty: Int) {
        val association = CartProductAssociation(cartId, productId, qty)
        cartDao.removeProductFromCart(association)
    }

    fun isProductInCart(productId: Long): CartProductAssociation? {
        return cartDao.isProductInCart(productId)
    }

    fun getProductsFromCart(): List<CartProductRelation> {
        return cartDao.getProducts()
    }

    fun getUserWithId(userId: Long) = userDao.getUserWithId(userId)

    fun getUserWithEmailAndPassword(email: String, password: String) = userDao.getUser(email, password)

    fun addUser(user: User) = userDao.addUser(user)
}