package com.gshot.step.mapper.domainviewmapper

import com.gshot.step.domain.model.Cart
import com.gshot.step.domain.model.User
import com.gshot.step.mapper.EntityModelMapper

class CartDomainViewMapper: EntityModelMapper<Cart, com.gshot.step.presentation.model.Cart> {

    override fun fromEntityToModel(entity: Cart): com.gshot.step.presentation.model.Cart {
        return com.gshot.step.presentation.model.Cart(
                entity.id,
                entity.user!!.id
        )
    }

    override fun fromModelToEntity(model: com.gshot.step.presentation.model.Cart): Cart {
        return Cart(
                model.id,
                User.getUserFromId(model.userId)
        )
    }
}