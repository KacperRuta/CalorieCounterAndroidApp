package pl.kr.myapplication.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pl.kr.myapplication.FirstConfiguration.UserConfigurationRepository
import pl.kr.myapplication.FirstConfiguration.userConfigurationData.UserConfiguration


class ConfigurationViewModel(app: Application): AndroidViewModel(app) {


    var configurated: Boolean = false
    var age: Int = 0
    var height: Int = 0
    var weight: Double = 0.0
    var gender: Int = 0
    var activity_lvl: Int = 0
    var weight_goal: Double = 0.0
    var calories: Int = 0
    var protein: Int = 0
    var fats: Int = 0
    var carbs: Int = 0
    var diet_choice: Int = 0


    private val configRepo = UserConfigurationRepository(app.applicationContext)


    fun startingConfig() {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            configurated = configRepo.isConfigurated()
            age = configRepo.getAge()
            height = configRepo.getHeight()
            weight = configRepo.getWeight()
            gender = configRepo.getGender()
            activity_lvl = configRepo.getActivityLevel()
            weight_goal = configRepo.getWeightGoal()
            calories = configRepo.getCalories()
            protein = configRepo.getProtein()
            fats = configRepo.getFats()
            carbs = configRepo.getCarbs()
            diet_choice = configRepo.getDietChoice()
        }
    }

    fun updateConfig() {
        val config = UserConfiguration(1,configurated,age,height,weight,gender,activity_lvl,weight_goal,calories,protein,fats,carbs,diet_choice)

        CoroutineScope(viewModelScope.coroutineContext).launch {
            configRepo.insertOrUpdate(config)
        }
    }
}