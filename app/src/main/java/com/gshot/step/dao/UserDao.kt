package com.gshot.step.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gshot.step.model.User

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User): Long

    @Query("SELECT * FROM users WHERE email=:email AND password=:password")
    fun getUser(email: String, password: String): User?
}