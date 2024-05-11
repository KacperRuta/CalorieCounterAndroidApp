package pl.kr.myapplication.FirstConfiguration.userConfigurationData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserConfiguration::class], version = 1)
abstract class UserConfigurationDatabase: RoomDatabase() {

    abstract fun configurationDao(): UserConfigurationDao
}

object ConfigurationDb{
        private var INSTANCE: UserConfigurationDatabase? = null

        fun getInstance(context: Context): UserConfigurationDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserConfigurationDatabase::class.java,
                        "configuration-database"
                    )
                        .addCallback(AppDatabaseUserConfigurationCallback()) // Dodaj callback
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
