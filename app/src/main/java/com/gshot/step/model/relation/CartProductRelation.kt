package com.gshot.step.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.gshot.step.model.Cart
import com.gshot.step.model.CartProductAssociation
import com.gshot.step.model.Product

data class CartProductRelation(
    @Embedded
    val cart: Cart,

    @Relation(entity = CartProductAssociation::class, parentColumn = "id", entityColumn = "cart_id")
    val products: List<Product>
)
