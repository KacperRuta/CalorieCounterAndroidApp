package pl.kr.myapplication.MealDetailsDatabase

import androidx.room.Query
import androidx.room.Upsert

interface MealDetailsDao {
    @Upsert
    suspend fun insertOrUpdateMeal(meal: MealDetails)

    @Query("SELECT * FROM MealDetails WHERE meal_name LIKE '%' || :word || '%'")
    fun findMeals(word: String): List<MealDetails>



}