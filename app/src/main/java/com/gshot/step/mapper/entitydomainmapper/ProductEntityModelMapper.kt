package com.gshot.step.mapper.entitydomainmapper

import com.gshot.step.data.entity.Product
import com.gshot.step.domain.model.Category
import com.gshot.step.domain.model.Price
import com.gshot.step.mapper.EntityModelMapper

class ProductEntityModelMapper: EntityModelMapper<Product, com.gshot.step.domain.model.Product> {

    override fun fromEntityToModel(entity: Product): com.gshot.step.domain.model.Product {
        return com.gshot.step.domain.model.Product(
                entity.id.toInt(),
                entity.productName,
                entity.productDescription,
                entity.productImage,
                Category.getFromId(entity.categoryId.toInt()),
                Price.make(entity.productPrice.toInt())
        )
    }

    override fun fromModelToEntity(model: com.gshot.step.domain.model.Product): Product {
        return Product(
                model.id.toLong(),
                model.category.id.toLong(),
                model.name,
                model.image,
                model.description,
                model.price.price.toFloat()
        )
    }

    fun fromEntityListToModelList(entities: List<Product>): List<com.gshot.step.domain.model.Product> {
        return entities.map { fromEntityToModel(it) }
    }
}