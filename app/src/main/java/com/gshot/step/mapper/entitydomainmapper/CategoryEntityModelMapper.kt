package com.gshot.step.mapper.entitydomainmapper

import com.gshot.step.data.entity.Category
import com.gshot.step.mapper.EntityModelMapper

class CategoryEntityModelMapper: EntityModelMapper<Category, com.gshot.step.domain.model.Category> {

    override fun fromEntityToModel(entity: Category): com.gshot.step.domain.model.Category {
        return com.gshot.step.domain.model.Category(
                entity.id.toInt(),
                entity.name
        )
    }

    override fun fromModelToEntity(model: com.gshot.step.domain.model.Category): Category {
        return Category(
                model.id.toLong(),
                model.name!!
        )
    }

    fun fromEntityListToModelList(entities: List<Category>): List<com.gshot.step.domain.model.Category> {
        return entities.map { fromEntityToModel(it) }
    }
}