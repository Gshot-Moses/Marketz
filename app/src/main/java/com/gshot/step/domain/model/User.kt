package com.gshot.step.domain.model

class User(
        val id: Int,
        val name: String?,
        val email: String?,
        val password: String?,
        var picture: String?
){
    companion object{
        fun getUserFromId(id: Int) = User(id, null, null, null, null)
    }
}