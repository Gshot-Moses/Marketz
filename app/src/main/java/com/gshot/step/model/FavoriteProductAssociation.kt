package com.gshot.step.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "favoriteProductAssociation", primaryKeys = ["favorite_id", "product_id"],
        foreignKeys = [
            ForeignKey(entity = Favorite::class, parentColumns = ["favorite_id"], childColumns = ["favorite_id"]),
            ForeignKey(entity = Product::class, parentColumns = ["product_id"], childColumns = ["product_id"])
        ])
data class FavoriteProductAssociation(
    @ColumnInfo(name = "favorite_id")
    val favoriteId: Long,
    @ColumnInfo(name = "product_id")
    val productId: Long,
)