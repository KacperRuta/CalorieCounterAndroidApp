package pl.kr.myapplication.MealMacrosDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AllUserMacros::class], version = 1)
abstract class AllUserMacrosDatabase: RoomDatabase() {

    abstract fun allUserMacrosDao(): AllUserMacrosDao
}


object AllUserMacrosDb{
    private var INSTANCE: AllUserMacrosDatabase? = null

    fun getInstance(context: Context): AllUserMacrosDatabase {
        synchronized(this) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AllUserMacrosDatabase::class.java,
                    "allusermacros-database"
                )
                    .addCallback(AppDatabaseAllUserMacriosCallback()) // Dodaj callback
                    .build()
            }
            return INSTANCE!!
        }
    }
}
