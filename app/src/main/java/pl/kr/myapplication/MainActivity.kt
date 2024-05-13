package pl.kr.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.FirstConfiguration.FirstConfigurationActivity
import pl.kr.myapplication.ViewModels.ConfigurationViewModel
import pl.kr.myapplication.ViewModels.MealViewModel
import pl.kr.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val configVm by viewModels<ConfigurationViewModel>()
    private val mealMacrosVm by viewModels<MealViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        configVm.startingConfig()
        mealMacrosVm.startingConfig()



        binding.mainButton.setOnClickListener {
            if (configVm.configurated == false) {
                val explicitIntent = Intent(applicationContext, FirstConfigurationActivity::class.java)
                startActivity(explicitIntent)
                finish()
            } else {
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




        setContentView(binding.root)
    }
}