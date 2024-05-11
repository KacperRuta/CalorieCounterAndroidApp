package pl.kr.myapplication.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pl.kr.myapplication.MealMacrosDatabase.AllUserMacrosRepository
import pl.kr.myapplication.Structures.Macros

class MealViewModel(app: Application): AndroidViewModel(app) {
    var current_macros = Macros(0,0,0,0)
    var meal_macros_1 = Macros(0,0,0,0)
    var meal_macros_2 = Macros(0,0,0,0)
    var meal_macros_3 = Macros(0,0,0,0)
    var meal_macros_4 = Macros(0,0,0,0)
    var meal_macros_5 = Macros(0,0,0,0)


    private val allMacrosRepo = AllUserMacrosRepository(app.applicationContext)



    fun startingConfig() {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            current_macros = Macros(allMacrosRepo.getCalories(0), allMacrosRepo.getProtein(0), allMacrosRepo.getFats(0), allMacrosRepo.getCarbs(0))
            meal_macros_1 = Macros(allMacrosRepo.getCalories(1), allMacrosRepo.getProtein(1), allMacrosRepo.getFats(1), allMacrosRepo.getCarbs(1))
            meal_macros_2 = Macros(allMacrosRepo.getCalories(2), allMacrosRepo.getProtein(2), allMacrosRepo.getFats(2), allMacrosRepo.getCarbs(2))
            meal_macros_3 = Macros(allMacrosRepo.getCalories(3), allMacrosRepo.getProtein(3), allMacrosRepo.getFats(3), allMacrosRepo.getCarbs(3))
            meal_macros_4 = Macros(allMacrosRepo.getCalories(4), allMacrosRepo.getProtein(4), allMacrosRepo.getFats(4), allMacrosRepo.getCarbs(4))
            meal_macros_5 = Macros(allMacrosRepo.getCalories(5), allMacrosRepo.getProtein(5), allMacrosRepo.getFats(5), allMacrosRepo.getCarbs(5))
        }
    }

    fun updateConfig(mealID: Int, meal_macros: Macros){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            allMacrosRepo.setValuesById(
                mealID,
                meal_macros.calories,
                meal_macros.protein,
                meal_macros.fats,
                meal_macros.carbs
            )
        }
    }
}