package com.gshot.step.mapper.domainviewmapper

import com.gshot.step.domain.model.User
import com.gshot.step.mapper.EntityModelMapper

class UserDomainViewMapper: EntityModelMapper<User, com.gshot.step.presentation.model.User> {

    override fun fromEntityToModel(entity: User): com.gshot.step.presentation.model.User {
        return com.gshot.step.presentation.model.User(
                entity.id,
                entity.name!!,
                entity.email!!,
                entity.password!!,
                entity.picture
        )
    }

    override fun fromModelToEntity(model: com.gshot.step.presentation.model.User): User {
        return User(
                model.id,
                model.name,
                model.email,
                model.password,
                model.picture
        )
    }
}