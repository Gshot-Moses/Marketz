package com.gshot.step.mapper.domainviewmapper

import com.gshot.step.domain.model.Price
import com.gshot.step.domain.model.Product
import com.gshot.step.mapper.EntityModelMapper
import com.gshot.step.presentation.model.Category

class ProductDomainViewMapper: EntityModelMapper<Product, com.gshot.step.presentation.model.Product> {

    override fun fromEntityToModel(entity: Product): com.gshot.step.presentation.model.Product {
        return com.gshot.step.presentation.model.Product(
                entity.id,
                entity.name,
                entity.description,
                entity.image,
                entity.price.toString(),
                entity.category.id
        )
    }

    override fun fromModelToEntity(model: com.gshot.step.presentation.model.Product): Product {
        return Product(
                model.id,
                model.name,
                model.description,
                model.image,
                com.gshot.step.domain.model.Category.getFromId(model.id),
                Price.make(model.price)
        )
    }

    fun fromDomainListToViewList(models: List<Product>): List<com.gshot.step.presentation.model.Product> {
        return models.map { fromEntityToModel(it) }
    }
}