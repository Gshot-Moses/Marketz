package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gshot.step.data.Repository
import com.gshot.step.data.database.AppDatabase
import com.gshot.step.domain.service.CartService
import com.gshot.step.mapper.domainviewmapper.CartDomainViewMapper
import com.gshot.step.mapper.entitydomainmapper.CartEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.UserEntityModelMapper
import com.gshot.step.presentation.model.Cart

class SharedViewModel(application: Application): AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getUserDao())
    private val cartService = CartService(repo, ProductEntityModelMapper(), CartEntityModelMapper(), UserEntityModelMapper())
    private val cartMapper = CartDomainViewMapper()

    fun checkCart(userId: Int): Boolean {
        return cartService.checkCart(userId)
    }

    fun createCart(userId: Int): Cart {
        cartService.createCart(userId)
        return cartMapper.fromEntityToModel(cartService.getCartWithUserId(userId))
    }

    fun getCart(userId: Int): Cart {
        return cartMapper.fromEntityToModel(cartService.getCartWithUserId(userId))
    }
}