package com.gshot.step.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "cartProductAssociation", primaryKeys = ["cart_id", "product_id"],
        foreignKeys = [
            ForeignKey(entity = Cart::class, parentColumns = ["id"], childColumns = ["cart_id"]),
            ForeignKey(entity = Product::class, parentColumns = ["id"], childColumns = ["product_id"])
        ])
data class CartProductAssociation(
    @ColumnInfo(name = "cart_id")
    val cartId: Long,
    @ColumnInfo(name = "product_id")
    val productId: Long
)