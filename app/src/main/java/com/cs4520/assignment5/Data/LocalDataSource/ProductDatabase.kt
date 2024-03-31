package com.cs4520.assignment4.Data.LocalDataSource;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase;
import com.cs4520.assignment4.Data.Entities.Product;

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    // Define an abstract method to get the DAO for the Product entity
    abstract fun productDao(): ProductDAO

    companion object {
        private var INSTANCE: ProductDatabase? = null
        fun getDatabase(context: Context): ProductDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,ProductDatabase::class.java, "contact_database")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}
