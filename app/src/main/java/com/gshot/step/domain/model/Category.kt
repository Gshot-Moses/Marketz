package com.gshot.step.domain.model

class Category(
        var id: Int,
        var name: String?
) {
    companion object {
        fun getFromId(id: Int) = Category(id, null)
    }
}