package com.gshot.step.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gshot.step.database.AppDatabase
import com.gshot.step.model.Category
import com.gshot.step.model.Product

class AddProductViewModel(application: Application): AndroidViewModel(application) {

    val dao = AppDatabase.getInstance(application).getProductDao()
    val categoryDao = AppDatabase.getInstance(application).getCategoryDao()

    fun addProduct(product: Product): LiveData<Long> {
        val result = dao.addProduct(product)
        val data: LiveData<Long> = MutableLiveData(result)
        return data
    }

    fun getAllCategories(): LiveData<List<Category>> = categoryDao.getCategories()
}