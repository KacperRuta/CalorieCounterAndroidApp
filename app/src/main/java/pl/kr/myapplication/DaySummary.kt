package pl.kr.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.Structures.Macros
import pl.kr.myapplication.ViewModels.ConfigurationViewModel
import pl.kr.myapplication.ViewModels.MealViewModel
import pl.kr.myapplication.databinding.ActivityDaySummaryBinding

class DaySummary : AppCompatActivity() {

    private lateinit var binding: ActivityDaySummaryBinding
    private val configVm by viewModels<ConfigurationViewModel>()
    private val mealMacrosVm by viewModels<MealViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaySummaryBinding.inflate(layoutInflater)
        configVm.startingConfig()
        mealMacrosVm.reset_meal_macros(1)
        mealMacrosVm.reset_meal_macros(2)
        mealMacrosVm.reset_meal_macros(3)
        mealMacrosVm.reset_meal_macros(4)
        mealMacrosVm.reset_meal_macros(5)

        var macros_goal = Macros(intent.getIntExtra("Calories",0),
            intent.getIntExtra("Protein",0),
            intent.getIntExtra("Fats",0),
            intent.getIntExtra("Carbs",0))

        val defaultIntArray = intArrayOf(0, 0, 0, 0)
        var current_macros = intent.getIntArrayExtra("Current_macros") ?: defaultIntArray

        var points = intent.getIntExtra("Points",0)

        var diet_type = intent.getIntExtra("Diet_type",0)
        var weight = intent.getDoubleExtra("Weight",0.0)
        var weight_goal = intent.getDoubleExtra("Weight_goal",0.0)

        var pointsGained = 0

        binding.showCaloriesOff.text = (current_macros[0] - macros_goal.calories).toString()
        if(weight_goal < weight) {
            if(current_macros[0] in macros_goal.calories - 100 .. macros_goal.calories + 25){
                pointsGained += 8
                binding.showPointsIncreseFromCalories.text = "+8"
            } else if (current_macros[0] in macros_goal.calories - 300 .. macros_goal.calories + 65) {
                pointsGained += 5
                binding.showPointsIncreseFromCalories.text = "+5"
            } else if (current_macros[0] in macros_goal.calories - 500 .. macros_goal.calories + 100) {
                pointsGained += 3
                binding.showPointsIncreseFromCalories.text = "+3"
            } else if (current_macros[0] in macros_goal.calories - 700 .. macros_goal.calories + 200) {
                pointsGained += 1
                binding.showPointsIncreseFromCalories.text = "+1"
            } else {
                binding.showPointsIncreseFromCalories.setTextColor(Color.RED)
                binding.showPointsIncreseFromCalories.text = "0"
            }
        } else if (weight_goal == weight) {
            if(current_macros[0] in macros_goal.calories - 50 .. macros_goal.calories + 50){
                pointsGained += 8
                binding.showPointsIncreseFromCalories.text = "+8"
            } else if (current_macros[0] in macros_goal.calories - 100 .. macros_goal.calories + 100) {
                pointsGained += 5
                binding.showPointsIncreseFromCalories.text = "+5"
            } else if (current_macros[0] in macros_goal.calories - 200 .. macros_goal.calories + 200) {
                pointsGained += 3
                binding.showPointsIncreseFromCalories.text = "+3"
            } else if (current_macros[0] in macros_goal.calories - 300 .. macros_goal.calories + 300) {
                pointsGained += 1
                binding.showPointsIncreseFromCalories.text = "+1"
            } else {
                binding.showPointsIncreseFromCalories.setTextColor(Color.RED)
                binding.showPointsIncreseFromCalories.text = "0"
            }
        } else if (weight_goal > weight) {
            if (current_macros[0] in macros_goal.calories - 25..macros_goal.calories + 100) {
                pointsGained += 8
                binding.showPointsIncreseFromCalories.text = "+8"
            } else if (current_macros[0] in macros_goal.calories - 65..macros_goal.calories + 300) {
                pointsGained += 5
                binding.showPointsIncreseFromCalories.text = "+5"
            } else if (current_macros[0] in macros_goal.calories - 100..macros_goal.calories + 500) {
                pointsGained += 3
                binding.showPointsIncreseFromCalories.text = "+3"
            } else if (current_macros[0] in macros_goal.calories - 200 .. macros_goal.calories + 700) {
                pointsGained += 1
                binding.showPointsIncreseFromCalories.text = "+1"
            } else {
                binding.showPointsIncreseFromCalories.setTextColor(Color.RED)
                binding.showPointsIncreseFromCalories.text = "0"
            }
        }

        if (diet_type == 1) {
            if(current_macros[1] in (macros_goal.protein * 0.8).toInt() .. (macros_goal.protein * 1.2).toInt()) {
                pointsGained += 2
                binding.showPointsIncreseFromProtein.text = "+2"
            } else if(current_macros[1] in (macros_goal.protein * 0.6).toInt() .. (macros_goal.protein * 1.4).toInt()) {
                pointsGained += 1
                binding.showPointsIncreseFromProtein.text = "+1"
            } else {
                binding.showPointsIncreseFromProtein.setTextColor(Color.RED)
                binding.showPointsIncreseFromProtein.text = "0"
            }

            if(current_macros[2] in (macros_goal.fats * 0.8).toInt() .. (macros_goal.fats * 1.2).toInt()) {
                pointsGained += 2
                binding.showPointsIncreseFromFats.text = "+2"
            } else if(current_macros[2] in (macros_goal.fats * 0.6).toInt() .. (macros_goal.fats * 1.4).toInt()) {
                pointsGained += 1
                binding.showPointsIncreseFromFats.text = "+1"
            } else {
                binding.showPointsIncreseFromFats.setTextColor(Color.RED)
                binding.showPointsIncreseFromFats.text = "0"
            }

            if(current_macros[3] in (macros_goal.carbs * 0.8).toInt() .. (macros_goal.carbs * 1.2).toInt()) {
                pointsGained += 2
                binding.showPointsIncreseFromCarbs.text = "+2"
            } else if(current_macros[3] in (macros_goal.carbs * 0.6).toInt() .. (macros_goal.carbs * 1.4).toInt()) {
                pointsGained += 1
                binding.showPointsIncreseFromCarbs.text = "+1"
            } else {
                binding.showPointsIncreseFromCarbs.setTextColor(Color.RED)
                binding.showPointsIncreseFromCarbs.text = "0"
            }
        } else if (diet_type == 2) {
            if(current_macros[1] in (macros_goal.protein * 0.95).toInt() .. (macros_goal.protein * 1.4).toInt()) {
                pointsGained += 4
                binding.showPointsIncreseFromProtein.text = "+4"
            } else if(current_macros[1] in (macros_goal.protein * 0.85).toInt() .. (macros_goal.protein * 2).toInt()) {
                pointsGained += 2
                binding.showPointsIncreseFromProtein.text = "+2"
            } else if(current_macros[1] > (macros_goal.protein * 0.75).toInt())  {
                pointsGained += 1
                binding.showPointsIncreseFromProtein.text = "+1"
            } else {
                binding.showPointsIncreseFromProtein.setTextColor(Color.RED)
                binding.showPointsIncreseFromProtein.text = "0"
            }

            if(current_macros[2] in (macros_goal.fats * 0.6).toInt() .. (macros_goal.fats * 1.4).toInt()) {
                pointsGained += 1
                binding.showPointsIncreseFromFats.text = "+1"
            } else {
                binding.showPointsIncreseFromFats.setTextColor(Color.RED)
                binding.showPointsIncreseFromFats.text = "0"
            }

            if(current_macros[3] in (macros_goal.carbs * 0.6).toInt() .. (macros_goal.carbs * 1.4).toInt()) {
                pointsGained += 1
                binding.showPointsIncreseFromCarbs.text = "+1"
            } else {
                binding.showPointsIncreseFromCarbs.setTextColor(Color.RED)
                binding.showPointsIncreseFromCarbs.text = "0"
            }
        } else if (diet_type == 3) {
            if(current_macros[2] in (macros_goal.fats * 0.9).toInt() .. (macros_goal.fats * 1.4).toInt()) {
                pointsGained += 4
                binding.showPointsIncreseFromFats.text = "+4"
            } else if(current_macros[2] in (macros_goal.fats * 0.8).toInt() .. (macros_goal.fats * 2).toInt()) {
                pointsGained += 2
                binding.showPointsIncreseFromFats.text = "+2"
            } else if(current_macros[2] > (macros_goal.fats * 0.7).toInt())  {
                pointsGained += 1
                binding.showPointsIncreseFromFats.text = "+1"
            } else {
                binding.showPointsIncreseFromFats.setTextColor(Color.RED)
                binding.showPointsIncreseFromFats.text = "0"
            }

            if(current_macros[1] in (macros_goal.protein * 0.6).toInt() .. (macros_goal.protein * 1.4).toInt()) {
                pointsGained += 1
                binding.showPointsIncreseFromProtein.text = "+1"
            } else {
                binding.showPointsIncreseFromProtein.setTextColor(Color.RED)
                binding.showPointsIncreseFromProtein.text = "0"
            }

            if(current_macros[3] in (macros_goal.carbs * 0.6).toInt() .. (macros_goal.carbs * 1.4).toInt()) {
                pointsGained += 1
                binding.showPointsIncreseFromCarbs.text = "+1"
            } else {
                binding.showPointsIncreseFromCarbs.setTextColor(Color.RED)
                binding.showPointsIncreseFromCarbs.text = "0"
            }
        }

        binding.allPointsGained.text = "+ " + pointsGained.toString()
        points += pointsGained

        binding.buttonProceed.setOnClickListener {
            configVm.user_points = points
            configVm.updateConfig()
            val explicitIntent = Intent(applicationContext, CalorieCounterActivity::class.java)
            explicitIntent.putExtra("Calories",configVm.calories)
            explicitIntent.putExtra("Protein",configVm.protein)
            explicitIntent.putExtra("Fats",configVm.fats)
            explicitIntent.putExtra("Carbs",configVm.carbs)

            var intArray = intArrayOf(mealMacrosVm.meal_macros_1.calories,
                mealMacrosVm.meal_macros_1.protein,
                mealMacrosVm.meal_macros_1.fats,
                mealMacrosVm.meal_macros_1.carbs)
            explicitIntent.putExtra("Meal_macros_1",intArray)

            intArray = intArrayOf(mealMacrosVm.meal_macros_2.calories,
                mealMacrosVm.meal_macros_2.protein,
                mealMacrosVm.meal_macros_2.fats,
                mealMacrosVm.meal_macros_2.carbs)
            explicitIntent.putExtra("Meal_macros_2",intArray)

            intArray = intArrayOf(mealMacrosVm.meal_macros_3.calories,
                mealMacrosVm.meal_macros_3.protein,
                mealMacrosVm.meal_macros_3.fats,
                mealMacrosVm.meal_macros_3.carbs)
            explicitIntent.putExtra("Meal_macros_3",intArray)

            intArray = intArrayOf(mealMacrosVm.meal_macros_4.calories,
                mealMacrosVm.meal_macros_4.protein,
                mealMacrosVm.meal_macros_4.fats,
                mealMacrosVm.meal_macros_4.carbs)
            explicitIntent.putExtra("Meal_macros_4",intArray)

            intArray = intArrayOf(mealMacrosVm.meal_macros_5.calories,
                mealMacrosVm.meal_macros_5.protein,
                mealMacrosVm.meal_macros_5.fats,
                mealMacrosVm.meal_macros_5.carbs)
            explicitIntent.putExtra("Meal_macros_5",intArray)

            explicitIntent.putExtra("Points",configVm.user_points)

            startActivity(explicitIntent)
            finish()
        }


        setContentView(binding.root)
    }
}