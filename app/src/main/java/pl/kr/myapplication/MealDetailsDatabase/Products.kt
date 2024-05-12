package pl.kr.myapplication.MealDetailsDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Products(
    @PrimaryKey val meal_name: String,
    val calories: Int,
    val protein: Int,
    val fats: Int,
    val carbs: Int
    )
