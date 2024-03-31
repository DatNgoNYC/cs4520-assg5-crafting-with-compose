package com.cs4520.assignment4.Data.LocalDataSource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cs4520.assignment4.Data.Entities.Product

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(product: List<Product>)

    @Query("Select * FROM products")
    fun getAllProducts(): List<Product>
}