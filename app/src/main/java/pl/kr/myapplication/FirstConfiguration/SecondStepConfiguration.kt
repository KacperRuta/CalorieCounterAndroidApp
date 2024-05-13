package pl.kr.myapplication.FirstConfiguration

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.CalorieCounterActivity
import pl.kr.myapplication.ViewModels.ConfigurationViewModel
import pl.kr.myapplication.ViewModels.MealViewModel
import pl.kr.myapplication.databinding.ActivitySecondStepConfigurationBinding
import kotlin.math.roundToInt

class SecondStepConfiguration : AppCompatActivity() {

    private val configVm by viewModels<ConfigurationViewModel>()
    private val mealMacrosVm by viewModels<MealViewModel>()

    private lateinit var binding: ActivitySecondStepConfigurationBinding

    var choice: Int = 0

    var calories: Int = 0
    var protein: Int = 0
    var fats: Int = 0
    var carbs: Int = 0

    fun calculate_calories(){
        // Liczenie kalorii:
        var bmr = 0.0
        // Harris-Benedict formula:
        if (configVm.gender == 1){
            bmr = 66.473 +
                    (13.7516 * configVm.weight) +
                    (5.0033  * configVm.height) -
                    (6.755 * configVm.age)
        } else {
            bmr = 655.0955 +
                    (9.5634 * configVm.weight) +
                    (1.8496 * configVm.height) -
                    (4.6756 * configVm.age)
        }

        // amr formula:
        if (configVm.activity_lvl == 1){
            bmr *= 1.2
        }
        if (configVm.activity_lvl == 2){
            bmr *= 1.4
        }
        if (configVm.activity_lvl == 3){
            bmr *= 1.6
        }
        if (configVm.activity_lvl == 4){
            bmr *= 1.8
        }

        // dostosowanie do celu:
        if (configVm.weight_goal < configVm.weight) {
            calories = (bmr - 500).roundToInt()
        }
        if (configVm.weight_goal == configVm.weight) {
            calories = bmr.roundToInt()
        }
        if (configVm.weight_goal > configVm.weight) {
            calories = (bmr + 500).roundToInt()
        }
        configVm.calories = calories
    }

    fun calculate_macros() {
        // Each 1g of protein and carbohydrate has 4kcal
        // Each 1g of fats has 9kcal

        // For balanced diet the percentage should look like: 15% / 30% / 55%
        if (choice == 1){
            val c = calories.toDouble()

            var h = (c * 0.15) / 4
            protein = h.roundToInt()
            binding.editProtein.text = protein.toString()+ "g"
            binding.TextViewShowProteinP.text = "15%"

            h = (c * 0.3) / 9
            fats = h.roundToInt()
            binding.editFats.text = fats.toString() + "g"
            binding.TextViewShowFatsP.text = "30%"

            h = (c * 0.55) / 4
            carbs = h.roundToInt()
            binding.editCarbs.text = carbs.toString() + "g"
            binding.TextViewShowCarbsP.text = "55%"
        }

        // High protein diet:
        if (choice == 2){
            val c = calories.toDouble()

            if (configVm.weight_goal < configVm.weight) {
                var h = (c * 0.35) / 4
                protein = h.roundToInt()
                binding.editProtein.text = protein.toString()+ "g"
                binding.TextViewShowProteinP.text = "35%"


                h = (c * 0.2) / 9
                fats = h.roundToInt()
                binding.editFats.text = fats.toString() + "g"
                binding.TextViewShowFatsP.text = "20%"

                h = (c * 0.45) / 4
                carbs = h.roundToInt()
                binding.editCarbs.text = carbs.toString() + "g"
                binding.TextViewShowCarbsP.text = "45%"
            } else {
                var h = (c * 0.3) / 4
                protein = h.roundToInt()
                binding.editProtein.text = protein.toString() + "g"
                binding.TextViewShowProteinP.text = "30%"


                h = (c * 0.25) / 9
                fats = h.roundToInt()
                binding.editFats.text = fats.toString() + "g"
                binding.TextViewShowFatsP.text = "25%"

                h = (c * 0.45) / 4
                carbs = h.roundToInt()
                binding.editCarbs.text = carbs.toString() + "g"
                binding.TextViewShowCarbsP.text = "45%"
            }
        }


        // For high fat diet the percentage look like:
        if (choice == 3){
            val c = calories.toDouble()

            var h = (c * 0.2) / 4
            protein = h.roundToInt()
            binding.editProtein.text = protein.toString()+ "g"
            binding.TextViewShowProteinP.text = "20%"


            h = (c * 0.7) / 9
            fats = h.roundToInt()
            binding.editFats.text = fats.toString() + "g"
            binding.TextViewShowFatsP.text = "70%"

            h = (c * 0.1) / 4
            carbs = h.roundToInt()
            binding.editCarbs.text = carbs.toString() + "g"
            binding.TextViewShowCarbsP.text = "10%"
        }

        configVm.protein = protein
        configVm.fats = fats
        configVm.carbs = carbs
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondStepConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configVm.startingConfig()
        mealMacrosVm.startingConfig()

        ////////////////////////////////////////////////////////////
        // Obsługa wyboru aktywnosci diety po przyciskach
        ////////////////////////////////////////////////////////////

        binding.buttonBallancedChoice.setOnClickListener {
            calculate_calories()
            binding.TextShowCalories.text = calories.toString()



            if(choice == 0){
                binding.buttonBallancedChoice.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                choice = 1
            }
            if(choice > 1){
                binding.buttonBallancedChoice.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                if(choice == 2){
                    binding.buttonHighProteinChoice.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(choice == 3){
                    binding.buttonHighFatChoice.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}

                choice = 1
            }
            calculate_macros()
        }

        binding.buttonHighProteinChoice.setOnClickListener {
            calculate_calories()
            binding.TextShowCalories.text = calories.toString()


            if(choice == 0){
                binding.buttonHighProteinChoice.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                choice = 2
            }
            if(choice == 1 || choice == 3){
                binding.buttonHighProteinChoice.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                if(choice == 1){
                    binding.buttonBallancedChoice.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(choice == 3){
                    binding.buttonHighFatChoice.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}


                choice = 2
            }
            calculate_macros()

        }

        binding.buttonHighFatChoice.setOnClickListener {
            calculate_calories()
            binding.TextShowCalories.text = calories.toString()


            if(choice == 0){
                binding.buttonHighFatChoice.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                choice = 3
            }
            if(choice == 1 || choice == 2){
                binding.buttonHighFatChoice.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                if(choice == 2){
                    binding.buttonHighProteinChoice.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}
                if(choice == 1){
                    binding.buttonBallancedChoice.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}


                choice = 3
            }
            calculate_macros()

        }

        binding.buttonEndConfiguration.setOnClickListener{
            if(choice != 0){
                configVm.diet_choice = choice
                configVm.configurated = true
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
        }

        }


    }
