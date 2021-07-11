package com.gshot.step.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.gshot.step.model.Favorite
import com.gshot.step.model.FavoriteProductAssociation
import com.gshot.step.model.Product

class FavoriteProductRelation(
    @Embedded
    val favorite: Favorite,

    @Relation(entity = FavoriteProductAssociation::class, parentColumn = "id", entityColumn = "favorite_id")
    val products: List<Product>
)