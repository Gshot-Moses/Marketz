package com.gshot.step.data.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gshot.step.data.entity.Favorite
import com.gshot.step.data.entity.FavoriteProductAssociation
import com.gshot.step.data.entity.Product

class FavoriteProductRelation(
    @Embedded
    val favorite: Favorite?,

    @Relation(parentColumn = "favorite_id", entityColumn = "product_id", associateBy = Junction(FavoriteProductAssociation::class))
    val products: List<Product> = emptyList()
)