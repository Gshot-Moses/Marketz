package com.gshot.step.mapper.entitydomainmapper

import com.gshot.step.data.entity.Cart
import com.gshot.step.domain.model.User
import com.gshot.step.mapper.EntityModelMapper

class CartEntityModelMapper: EntityModelMapper<Cart, com.gshot.step.domain.model.Cart> {

    override fun fromEntityToModel(entity: Cart): com.gshot.step.domain.model.Cart {
        return com.gshot.step.domain.model.Cart(
                entity.id!!.toInt(),
                User.getUserFromId(entity.userId!!.toInt())
        )
    }

    override fun fromModelToEntity(model: com.gshot.step.domain.model.Cart): Cart {
        return Cart(
                model.id.toLong(),
                model.user!!.id.toLong()
        )
    }
}