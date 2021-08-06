package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gshot.step.data.Repository
import com.gshot.step.data.database.AppDatabase
import com.gshot.step.domain.service.CartService
import com.gshot.step.domain.service.UserService
import com.gshot.step.mapper.entitydomainmapper.CartEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.ProductEntityModelMapper
import com.gshot.step.mapper.entitydomainmapper.UserEntityModelMapper
import com.gshot.step.mapper.domainviewmapper.CartDomainViewMapper
import com.gshot.step.mapper.domainviewmapper.UserDomainViewMapper
import com.gshot.step.presentation.model.Cart
import com.gshot.step.presentation.model.User

class SignInFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getUserDao())
    private val userService = UserService(repo , UserEntityModelMapper())
    private val userMapper = UserDomainViewMapper()

    fun signIn(email: String, password: String): User? {
        val user = userService.signIn(email, password)
        return user?.let { userMapper.fromEntityToModel(it) }
    }
}