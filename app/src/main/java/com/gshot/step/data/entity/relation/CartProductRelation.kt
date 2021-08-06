package com.gshot.step.data.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gshot.step.data.entity.Cart
import com.gshot.step.data.entity.CartProductAssociation
import com.gshot.step.data.entity.Product

data class CartProductRelation(
    @Embedded
    val cart: Cart?,

    @Relation(entity = Product::class, parentColumn = "cart_id", entityColumn = "product_id",
            associateBy = Junction(CartProductAssociation::class, parentColumn = "cart_id", entityColumn = "product_id"))
    val products: List<Product> = emptyList()
)
