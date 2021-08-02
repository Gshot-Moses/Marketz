package com.gshot.step.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize

@Entity(tableName = "products",
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["category_id"],
        childColumns = ["category_id"]
    )])
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    val id: Long,
    @ColumnInfo(name = "category_id")
    val categoryId: Long,
    @ColumnInfo(name = "name")
    val productName: String?,
    @ColumnInfo(name = "image")
    val productImage: String?,
    @ColumnInfo(name = "description")
    val productDescription: String?,
    @ColumnInfo(name = "price")
    val productPrice: Float,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(categoryId)
        parcel.writeString(productName)
        parcel.writeString(productImage)
        parcel.writeString(productDescription)
        parcel.writeFloat(productPrice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}