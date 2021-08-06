package com.gshot.step.domain.model

class Price(val price: Int) {

    override fun toString(): String {
        return "FCFA $price"
    }

    companion object {

        fun make(price: Int) = Price(price)

        fun make(price: String) = Price(price.substring(5).toInt())
    }
}