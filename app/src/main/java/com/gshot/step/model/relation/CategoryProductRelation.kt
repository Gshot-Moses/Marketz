package com.gshot.step.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.gshot.step.model.Category
import com.gshot.step.model.Product

data class CategoryProductRelation(
    @Embedded
    val category: Category,
    @Relation(entity = Product::class, parentColumn = "id", entityColumn = "category_id")
    val products: List<Product>
)