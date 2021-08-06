package com.gshot.step.domain.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gshot.step.data.Repository
import com.gshot.step.domain.model.Cart
import com.gshot.step.domain.model.Product
import com.gshot.step.mapper.entitydomainmapper.CartEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.UserEntityModelMapper

class CartService(val repo: Repository, val productMapper: ProductEntityModelMapper,
                  val cartMapper: CartEntityModelMapper, val userMapper: UserEntityModelMapper){

    fun createCart(userId: Int) {
        repo.createCart(userId.toLong())
    }

    fun checkCart(userId: Int): Boolean {
        return repo.getCartWithUserId(userId.toLong()) != null
    }

    fun getCartWithId(cartId: Int): Cart {
        val entity = repo.getCartWithId(cartId.toLong())
        val user = repo.getUserWithId(entity.userId!!)
        val model = cartMapper.fromEntityToModel(entity)
        model.user = userMapper.fromEntityToModel(user)
        return model
    }

    fun getCartWithUserId(userId: Int): Cart {
        val entity = repo.getCartWithUserId(userId.toLong())
        val user = repo.getUserWithId(entity?.userId!!)
        val model = cartMapper.fromEntityToModel(entity)
        model.user = userMapper.fromEntityToModel(user)
        return model
    }

    fun addProductToCart(cartId: Int, productId: Int, qty: Int) {
        repo.addProductToCart(productId.toLong(), cartId.toLong(), qty)
    }

    fun isProductInCart(productId: Int): Boolean {
        return repo.isProductInCart(productId.toLong()) != null
    }

    fun removeProductFromCart(cartId: Int, productId: Int, qty: Int) {
        repo.removeProductFromCart(productId.toLong(), cartId.toLong(), qty)
    }

    fun updateProductQuantity(qty: Int, cartId: Int) {
        repo.updateProductQuantity(qty, cartId)
    }

    fun getProductsFromCart(cartId: Int): LiveData<List<Product>> {
        val result = repo.getProductsFromCart().map { it.products }
        val data: LiveData<List<Product>> = MutableLiveData(productMapper.fromEntityListToModelList(result[0]))
        return data
    }
}