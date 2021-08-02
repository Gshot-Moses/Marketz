package com.gshot.step.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gshot.step.model.Cart
import com.gshot.step.model.CartProductAssociation
import com.gshot.step.model.Product

data class CartProductRelation(
    @Embedded
    val cart: Cart?,

    @Relation(entity = Product::class, parentColumn = "cart_id", entityColumn = "product_id",
            associateBy = Junction(CartProductAssociation::class, parentColumn = "cart_id", entityColumn = "product_id"))
    val products: List<Product> = emptyList()
)
