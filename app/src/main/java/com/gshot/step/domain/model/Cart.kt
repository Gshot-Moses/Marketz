package com.gshot.step.domain.model

class Cart(
        val id: Int,
        var user: User?
){
    companion object {
        fun getCart(id: Int) = Cart(id, null)
    }
}