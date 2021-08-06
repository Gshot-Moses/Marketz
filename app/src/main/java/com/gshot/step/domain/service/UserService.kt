package com.gshot.step.domain.service

import com.gshot.step.data.Repository
import com.gshot.step.domain.model.User
import com.gshot.step.mapper.entitydomainmapper.UserEntityModelMapper

class UserService(private val repo: Repository, private val userMapper: UserEntityModelMapper) {

    fun signUp(username: String, email: String, password: String): User? {
        val user = repo.getUserWithEmailAndPassword(email, password)
        return if (user == null) {
            val result = repo.addUser(userMapper.fromModelToEntity(User(0, username, email, password, null)))
            val user1 = repo.getUserWithEmailAndPassword(email, password)
            user1?.let { userMapper.fromEntityToModel(it) }
        }
        else {
            null
        }
    }

    fun signIn(email: String, password: String): User? {
        val user = repo.getUserWithEmailAndPassword(email, password)
        return user?.let { userMapper.fromEntityToModel(it) }
    }
}
