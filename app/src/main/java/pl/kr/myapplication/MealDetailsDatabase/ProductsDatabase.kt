package pl.kr.myapplication.MealDetailsDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.kr.myapplication.MealMacrosDatabase.AppDatabaseAllUserMacriosCallback

@Database(entities = [Products::class], version = 1)

abstract class ProductsDatabase: RoomDatabase() {

    abstract fun allUserMacrosDao(): ProductsDao
}


object AllUserMacrosDb{
    private var INSTANCE: ProductsDatabase? = null

    fun getInstance(context: Context): ProductsDatabase {
        synchronized(this) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "products-database"
                )
                    .addCallback(AppDatabaseAllUserMacriosCallback()) // Dodaj callback
                    .build()
            }
            return INSTANCE!!
        }
    }
}