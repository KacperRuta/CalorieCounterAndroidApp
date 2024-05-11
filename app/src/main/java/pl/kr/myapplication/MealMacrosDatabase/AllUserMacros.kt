package pl.kr.myapplication.MealMacrosDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class AllUserMacros(
    @PrimaryKey val id: Int,
    val calories: Int,
    val protein: Int,
    val fats: Int,
    val carbs: Int
)

