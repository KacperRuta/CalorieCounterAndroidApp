package pl.kr.myapplication.MealMacrosDatabase

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class AppDatabaseAllUserMacriosCallback  : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        // Wstawianie rekordu z wartościami domyślnymi do tabeli UserConfiguration
        db.execSQL("INSERT INTO AllUserMacros (id, calories, protein, fats, carbs) VALUES (0, 0, 0, 0, 0)")
        db.execSQL("INSERT INTO AllUserMacros (id, calories, protein, fats, carbs) VALUES (1, 0, 0, 0, 0)")
        db.execSQL("INSERT INTO AllUserMacros (id, calories, protein, fats, carbs) VALUES (2, 0, 0, 0, 0)")
        db.execSQL("INSERT INTO AllUserMacros (id, calories, protein, fats, carbs) VALUES (3, 0, 0, 0, 0)")
        db.execSQL("INSERT INTO AllUserMacros (id, calories, protein, fats, carbs) VALUES (4, 0, 0, 0, 0)")
        db.execSQL("INSERT INTO AllUserMacros (id, calories, protein, fats, carbs) VALUES (5, 0, 0, 0, 0)")
    }
}