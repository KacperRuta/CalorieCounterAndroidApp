package pl.kr.myapplication.ProductsDatabasePackage

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class ProductsDatabaseCallback  : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        db.execSQL("INSERT INTO Product (meal_name, calories, protein, fats, carbs) VALUES ('Tomato', 20, 1, 0, 4)")
    }
}