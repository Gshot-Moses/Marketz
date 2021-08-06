package com.gshot.step.mapper

interface EntityModelMapper<Entity, Model> {

    fun fromEntityToModel(entity: Entity): Model

    fun fromModelToEntity(model: Model): Entity
}