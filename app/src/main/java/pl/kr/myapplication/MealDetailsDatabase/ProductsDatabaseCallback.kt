package pl.kr.myapplication.MealDetailsDatabase

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class ProductsDatabaseCallback  : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
    }
}