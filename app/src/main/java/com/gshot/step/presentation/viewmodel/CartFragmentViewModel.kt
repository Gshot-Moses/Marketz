package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gshot.step.database.AppDatabase

class CartFragmentViewModel(application: Application): AndroidViewModel(application) {

    val cartDao = AppDatabase.getInstance(application).getCartDao()

    fun getProducts() = cartDao.getProducts()
}