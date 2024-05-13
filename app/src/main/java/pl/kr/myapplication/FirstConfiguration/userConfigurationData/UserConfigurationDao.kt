package pl.kr.myapplication.FirstConfiguration.userConfigurationData

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserConfigurationDao {
    // Upsert - dodanie nowej konfiguracji lub aktualizacja istniejącej
    @Upsert
    suspend fun insertOrUpdate(userConfiguration: UserConfiguration)

    // Pobranie wartości poszczególnych zmiennych konfiguracji
    @Query("SELECT configurated FROM UserConfiguration WHERE id = 1")
    suspend fun isConfigurated(): Boolean

    @Query("SELECT age FROM UserConfiguration WHERE id = 1")
    suspend fun getAge(): Int

    @Query("SELECT height FROM UserConfiguration WHERE id = 1")
    suspend fun getHeight(): Int

    @Query("SELECT weight FROM UserConfiguration WHERE id = 1")
    suspend fun getWeight(): Double

    @Query("SELECT gender FROM UserConfiguration WHERE id = 1")
    suspend fun getGender(): Int

    @Query("SELECT activity_lvl FROM UserConfiguration WHERE id = 1")
    suspend fun getActivityLevel(): Int

    @Query("SELECT weight_goal FROM UserConfiguration WHERE id = 1")
    suspend fun getWeightGoal(): Double

    @Query("SELECT calories FROM UserConfiguration WHERE id = 1")
    suspend fun getCalories(): Int

    @Query("SELECT protein FROM UserConfiguration WHERE id = 1")
    suspend fun getProtein(): Int

    @Query("SELECT fats FROM UserConfiguration WHERE id = 1")
    suspend fun getFats(): Int

    @Query("SELECT carbs FROM UserConfiguration WHERE id = 1")
    suspend fun getCarbs(): Int

    @Query("SELECT diet_choice FROM UserConfiguration WHERE id = 1")
    suspend fun getDietChoice(): Int

    @Query("SELECT user_points FROM UserConfiguration WHERE id = 1")
    suspend fun getUserPoints(): Int
}