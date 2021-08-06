package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gshot.step.data.Repository
import com.gshot.step.data.database.AppDatabase
import com.gshot.step.domain.service.CartService
import com.gshot.step.mapper.entitydomainmapper.CartEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.UserEntityModelMapper
import com.gshot.step.mapper.domainviewmapper.ProductDomainViewMapper
import com.gshot.step.presentation.model.Product

class CartFragmentViewModel(application: Application): AndroidViewModel(application) {

    val database = AppDatabase.getInstance(application)
    val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getUserDao())
    val cartService = CartService(repo, ProductEntityModelMapper(), CartEntityModelMapper(), UserEntityModelMapper())
    val productMapper = ProductDomainViewMapper()

    fun getProducts(cartId: Int): LiveData<List<Product>?> {
        val result = cartService.getProductsFromCart(cartId).value
        val data: LiveData<List<Product>?> = MutableLiveData(result?.let { productMapper.fromDomainListToViewList(it) })
        return data
    }

    fun updateProductQty(qty: Int, cartId: Int) {
        cartService.updateProductQuantity(qty, cartId)
    }

    fun removeProductFromCart(cartId: Int, productId: Int, qty: Int) {
        cartService.removeProductFromCart(cartId, productId, qty)
    }
}