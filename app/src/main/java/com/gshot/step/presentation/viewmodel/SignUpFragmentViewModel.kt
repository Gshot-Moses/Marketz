package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gshot.step.data.Repository
import com.gshot.step.data.database.AppDatabase
import com.gshot.step.domain.service.UserService
import com.gshot.step.mapper.entitydomainmapper.UserEntityModelMapper
import com.gshot.step.mapper.domainviewmapper.UserDomainViewMapper
import com.gshot.step.presentation.model.User

class SignUpFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val repo = Repository(database.getCategoryDao(), database.getCartDao(), database.getUserDao())
    private val userService = UserService(repo, UserEntityModelMapper())
    private val userMapper = UserDomainViewMapper()

    fun signUp(username: String, email: String, password: String): User? {
        return userService.signUp(username, email, password)?.let { userMapper.fromEntityToModel(it) }
    }
}