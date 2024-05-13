package pl.kr.myapplication.FirstConfiguration.userConfigurationData

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class AppDatabaseUserConfigurationCallback : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        // Wstawianie rekordu z wartościami domyślnymi do tabeli UserConfiguration
        db.execSQL("INSERT INTO UserConfiguration (id, configurated, age, height, weight, gender, activity_lvl, weight_goal, calories, protein, fats, carbs, diet_choice, user_points) VALUES (1, 0, 0, 0, 0.0, 0, 0, 0.0, 0, 0, 0, 0, 0, 0)")
    }
}
