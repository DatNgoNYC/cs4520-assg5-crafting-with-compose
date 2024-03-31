package com.cs4520.assignment4.Data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product (
    @PrimaryKey val name: String,
    val type: String,
    val expiryDate: String?,
    val price: String
)