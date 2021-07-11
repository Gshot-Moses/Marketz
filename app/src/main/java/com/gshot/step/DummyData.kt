package com.gshot.step

import com.gshot.step.model.Category
import com.gshot.step.model.Product

class DummyData {

    companion object {

        fun getCategories(): List<Category> {
            val categories = mutableListOf<Category>()
            for( i in 1..10) {
                categories.add(Category(0, "category${i}"))
            }
            return categories
        }

        fun getProducts(): List<Product> {
            val products = mutableListOf<Product>()
            for( i in 1..10) {
                products.add(
                    Product(0, 0,
                        "product${i}", "",
                        "description${i}", 21F))
            }
            return products
        }
    }
}