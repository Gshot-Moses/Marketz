package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gshot.step.data.Repository
import com.gshot.step.data.database.AppDatabase
import com.gshot.step.domain.service.CartService
import com.gshot.step.mapper.entitydomainmapper.CartEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.UserEntityModelMapper

class ProductDetailsFragmentViewModel(application: Application): AndroidViewModel(application) {

    val database = AppDatabase.getInstance(application)
    val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getUserDao())
    val cartService = CartService(repo, ProductEntityModelMapper(), CartEntityModelMapper(), UserEntityModelMapper())

    fun isProductInCart(productId: Int): Boolean {
        return cartService.isProductInCart(productId)
    }

    fun addProductToCart(productId: Int, cartId: Int, qty: Int) {
        cartService.addProductToCart(cartId, productId, qty)
    }
}