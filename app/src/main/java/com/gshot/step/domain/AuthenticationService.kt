package com.gshot.step.domain

import android.content.Context
import com.gshot.step.dao.UserDao
import com.gshot.step.database.AppDatabase
import com.gshot.step.model.User

class AuthenticationService private constructor(context: Context) {

    val userDao: UserDao

    init {
        userDao = AppDatabase.getInstance(context).getUserDao()
    }

    fun signUp(username: String, email: String, password: String): User? {
        val user = userDao.getUser(email, password)
        return if (user == null) {
            val result = userDao.addUser(User(0, username, email, password, null))
            userDao.getUser(email, password)
        }
        else {
            null
        }
    }

    fun signIn(email: String, password: String): User? {
        return userDao.getUser(email, password)
    }

    companion object {
        @Volatile var INSTANCE: AuthenticationService? = null

        fun getInstance(context: Context): AuthenticationService {
            return INSTANCE ?: synchronized(this) {
                AuthenticationService(context).also { INSTANCE = it }
            }
        }
    }
}
