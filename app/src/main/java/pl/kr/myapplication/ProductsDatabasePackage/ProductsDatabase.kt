package pl.kr.myapplication.ProductsDatabasePackage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)

abstract class ProductsDatabase: RoomDatabase() {

    abstract fun productsDao(): ProductsDao
}


object ProductsDb{
    private var INSTANCE: ProductsDatabase? = null

    fun getInstance(context: Context): ProductsDatabase {
        synchronized(this) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "products-database"
                )
                    .addCallback(ProductsDatabaseCallback()) // Dodaj callback
                    .build()
            }
            return INSTANCE!!
        }
    }
}