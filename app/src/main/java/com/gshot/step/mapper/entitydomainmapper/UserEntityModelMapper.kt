package com.gshot.step.mapper.entitydomainmapper

import com.gshot.step.data.entity.User
import com.gshot.step.mapper.EntityModelMapper

class UserEntityModelMapper: EntityModelMapper<User, com.gshot.step.domain.model.User> {

    override fun fromEntityToModel(entity: User): com.gshot.step.domain.model.User {
        return com.gshot.step.domain.model.User(
                entity.id.toInt(),
                entity.name,
                entity.email,
                entity.password,
                entity.picture
        )
    }

    override fun fromModelToEntity(model: com.gshot.step.domain.model.User): User {
        return User(
                model.id.toLong(),
                model.name!!,
                model.email!!,
                model.password!!,
                model.picture
        )
    }
}