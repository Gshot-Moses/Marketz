package com.gshot.step.mapper.domainviewmapper

import com.gshot.step.domain.model.Category
import com.gshot.step.mapper.EntityModelMapper

class CategoryDomainViewMapper: EntityModelMapper<Category, com.gshot.step.presentation.model.Category> {

    override fun fromEntityToModel(entity: Category): com.gshot.step.presentation.model.Category {
        return com.gshot.step.presentation.model.Category(
                entity.id,
                entity.name!!
        )
    }

    override fun fromModelToEntity(model: com.gshot.step.presentation.model.Category): Category {
        return Category(
                model.id,
                model.name
        )
    }

    fun fromModelListToEntityList(entities: List<Category>): List<com.gshot.step.presentation.model.Category> {
        return entities.map { fromEntityToModel(it) }
    }
}