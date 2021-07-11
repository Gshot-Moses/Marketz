package com.gshot.step.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "products",
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["category_id"]
    )])
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "category_id")
    val categoryId: Long,
    @ColumnInfo(name = "name")
    val productName: String,
    @ColumnInfo(name = "image")
    val productImage: String,
    @ColumnInfo(name = "description")
    val productDescription: String,
    @ColumnInfo(name = "price")
    val productPrice: Float,
)