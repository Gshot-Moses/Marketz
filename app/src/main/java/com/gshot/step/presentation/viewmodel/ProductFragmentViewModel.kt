package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.gshot.step.database.AppDatabase
import com.gshot.step.model.Product

class ProductFragmentViewModel(application: Application): AndroidViewModel(application) {

    val database = AppDatabase.getInstance(application)
    val categoryDao = database.getCategoryDao()
    var categoryId: Int = -1

    fun getProducts(categoryId: Int): LiveData<List<Product>> = categoryDao.getProducts(categoryId)
}