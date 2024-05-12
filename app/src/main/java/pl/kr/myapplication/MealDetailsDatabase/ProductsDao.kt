package pl.kr.myapplication.MealDetailsDatabase

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ProductsDao {
    @Upsert
    suspend fun insertOrUpdateMeal(meal: Products)

    @Query("SELECT * FROM Products WHERE meal_name LIKE '%' || :word || '%'")
    fun findMeals(word: String): List<Products>

    @Query("SELECT calories FROM Products WHERE meal_name = :mealID ")
    suspend fun getCalories(mealID: String): Int

    @Query("SELECT protein FROM Products WHERE meal_name = :mealID ")
    suspend fun getProtein(mealID: String): Int

    @Query("SELECT fats FROM Products WHERE meal_name = :mealID ")
    suspend fun getFats(mealID: String): Int

    @Query("SELECT carbs FROM Products WHERE meal_name = :mealID ")
    suspend fun getCarbs(mealID: String): Int

    @Query("SELECT meal_name FROM Products WHERE meal_name = :mealID ")
    suspend fun getMealName(mealID: String): String
    
}