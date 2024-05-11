package pl.kr.myapplication.MealMacrosDatabase

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface AllUserMacrosDao {
    // Upsert - dodanie nowej konfiguracji lub aktualizacja istniejącej
    @Upsert
    suspend fun insertOrUpdate(allUserMacros: AllUserMacros)

    @Query("UPDATE AllUserMacros SET calories = :calories, protein = :protein, fats = :fats, carbs = :carbs WHERE id = :userId")
    suspend fun setValuesById(userId: Int, calories: Int, protein: Int, fats: Int, carbs: Int)

    @Query("SELECT calories FROM AllUserMacros WHERE id = :mealID ")
    suspend fun getCalories(mealID: Int): Int

    @Query("SELECT protein FROM AllUserMacros WHERE id = :mealID ")
    suspend fun getProtein(mealID: Int): Int

    @Query("SELECT fats FROM AllUserMacros WHERE id = :mealID ")
    suspend fun getFats(mealID: Int): Int

    @Query("SELECT carbs FROM AllUserMacros WHERE id = :mealID ")
    suspend fun getCarbs(mealID: Int): Int

    @Query("UPDATE AllUserMacros SET calories = 0, protein = 0, fats = 0, carbs = 0 WHERE id = :userId")
    suspend fun resetValuesById(userId: Int)

}