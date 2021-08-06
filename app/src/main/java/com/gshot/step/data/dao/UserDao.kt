package com.gshot.step.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gshot.step.data.entity.User

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User): Long

    @Query("SELECT * FROM users WHERE user_id=:userId")
    fun getUserWithId(userId: Long): User

    @Query("SELECT * FROM users WHERE email=:email AND password=:password")
    fun getUser(email: String, password: String): User?
}