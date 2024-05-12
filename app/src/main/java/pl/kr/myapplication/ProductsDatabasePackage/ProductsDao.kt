package pl.kr.myapplication.ProductsDatabasePackage

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ProductsDao {
    @Upsert
    suspend fun insertOrUpdateMeal(meal: Product)

    @Query("SELECT * FROM Product WHERE meal_name LIKE '%' || :word || '%'")
    suspend fun findMeals(word: String): List<Product>

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT calories FROM Product WHERE meal_name = :mealID ")
    suspend fun getCalories(mealID: String): Int

    @Query("SELECT protein FROM Product WHERE meal_name = :mealID ")
    suspend fun getProtein(mealID: String): Int

    @Query("SELECT fats FROM Product WHERE meal_name = :mealID ")
    suspend fun getFats(mealID: String): Int

    @Query("SELECT carbs FROM Product WHERE meal_name = :mealID ")
    suspend fun getCarbs(mealID: String): Int

    @Query("SELECT meal_name FROM Product WHERE meal_name = :mealID ")
    suspend fun getMealName(mealID: String): String
    
}