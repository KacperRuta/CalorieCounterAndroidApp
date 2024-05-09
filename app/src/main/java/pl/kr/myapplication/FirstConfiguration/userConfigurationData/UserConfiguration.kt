package pl.kr.myapplication.FirstConfiguration.userConfigurationData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserConfiguration(    @PrimaryKey val id: Int = 1,
                                 val configurated: Boolean,
                                 val age: Int,
                                 val height: Int,
                                 val weight: Double,
                                 val gender: Int,
                                 val activity_lvl: Int,
                                 val weight_goal: Double,
                                 val calories: Int,
                                 val protein: Int,
                                 val fats: Int,
                                 val carbs: Int,
                                 val diet_choice: Int)


