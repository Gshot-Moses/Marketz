package com.gshot.step.domain.model

class Product(
        val id: Int,
        val name: String,
        val description: String,
        val image: String,
        val category: Category,
        val price: Price
)