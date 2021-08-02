package com.gshot.step.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gshot.step.model.Favorite
import com.gshot.step.model.FavoriteProductAssociation
import com.gshot.step.model.Product

class FavoriteProductRelation(
    @Embedded
    val favorite: Favorite?,

    @Relation(parentColumn = "favorite_id", entityColumn = "product_id", associateBy = Junction(FavoriteProductAssociation::class))
    val products: List<Product> = emptyList()
)