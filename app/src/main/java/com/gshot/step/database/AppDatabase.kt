package com.gshot.step.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gshot.step.dao.*
import com.gshot.step.model.*

@Database(entities = [
                        Cart::class,
                        CartProductAssociation::class,
                        Category::class,
                        Favorite::class,
                        FavoriteProductAssociation::class,
                        Product::class,
                        User::class],
        version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getCartDao(): CartDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getFavoriteDao(): FavoriteDao
    abstract fun getProductDao(): ProductDao
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        val DBNAME = "market"

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?:  synchronized(this) {
                INSTANCE ?: buildDatabase(context).also{ INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DBNAME).build()
        }
    }
}