package com.gshot.step.dao

import androidx.room.Dao
import androidx.room.Insert
import com.gshot.step.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: User): Long
}