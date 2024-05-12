package pl.kr.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.FirstConfiguration.FirstConfigurationActivity
import pl.kr.myapplication.Structures.Macros
import pl.kr.myapplication.ViewModels.ConfigurationViewModel
import pl.kr.myapplication.ViewModels.MealViewModel
import pl.kr.myapplication.databinding.ActivityCalorieCounterBinding

class CalorieCounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalorieCounterBinding
    private val configVm by viewModels<ConfigurationViewModel>()
    private val mealMacrosVm by viewModels<MealViewModel>()


    var macros_goal:Macros = Macros(0,0,0,0)

    var points: Int = 0

    fun show_meals(){
        binding.ShowCalories.text = "Calories:    " + mealMacrosVm.current_macros.calories.toString() + "  /  " + macros_goal.calories.toString()
        binding.ShowProtein.text = mealMacrosVm.current_macros.protein.toString() + " / " + macros_goal.protein.toString()
        binding.ShowFats.text = mealMacrosVm.current_macros.fats.toString() + " / " + macros_goal.fats.toString()
        binding.ShowCarbs.text = mealMacrosVm.current_macros.carbs.toString() + " / " + macros_goal.carbs.toString()

        if(mealMacrosVm.meal_macros_1.calories != 0){
            binding.ShowMeal1Calories.text = mealMacrosVm.meal_macros_1.calories.toString() + " kcal"
            binding.ShowMeal1Protein.text = mealMacrosVm.meal_macros_1.protein.toString() + " p"
            binding.ShowMeal1Fats.text = mealMacrosVm.meal_macros_1.fats.toString() + " f"
            binding.ShowMeal1Carbs.text = mealMacrosVm.meal_macros_1.carbs.toString() + " c"
        }
        if(mealMacrosVm.meal_macros_2.calories != 0){
            binding.ShowMeal2Calories.text = mealMacrosVm.meal_macros_2.calories.toString() + " kcal"
            binding.ShowMeal2Protein.text = mealMacrosVm.meal_macros_2.protein.toString() + " p"
            binding.ShowMeal2Fats.text = mealMacrosVm.meal_macros_2.fats.toString() + " f"
            binding.ShowMeal2Carbs.text = mealMacrosVm.meal_macros_2.carbs.toString() + " c"
        }
        if(mealMacrosVm.meal_macros_3.calories != 0){
            binding.ShowMeal3Calories.text = mealMacrosVm.meal_macros_3.calories.toString() + " kcal"
            binding.ShowMeal3Protein.text = mealMacrosVm.meal_macros_3.protein.toString() + " p"
            binding.ShowMeal3Fats.text = mealMacrosVm.meal_macros_3.fats.toString() + " f"
            binding.ShowMeal3Carbs.text = mealMacrosVm.meal_macros_3.carbs.toString() + " c"
        }
        if(mealMacrosVm.meal_macros_4.calories != 0){
            binding.ShowMeal4Calories.text = mealMacrosVm.meal_macros_4.calories.toString() + " kcal"
            binding.ShowMeal4Protein.text = mealMacrosVm.meal_macros_4.protein.toString() + " p"
            binding.ShowMeal4Fats.text = mealMacrosVm.meal_macros_4.fats.toString() + " f"
            binding.ShowMeal4Carbs.text = mealMacrosVm.meal_macros_4.carbs.toString() + " c"
        }
        if(mealMacrosVm.meal_macros_5.calories != 0){
            binding.ShowMeal5Calories.text = mealMacrosVm.meal_macros_5.calories.toString() + " kcal"
            binding.ShowMeal5Protein.text = mealMacrosVm.meal_macros_5.protein.toString() + " p"
            binding.ShowMeal5Fats.text = mealMacrosVm.meal_macros_5.fats.toString() + " f"
            binding.ShowMeal5Carbs.text = mealMacrosVm.meal_macros_5.carbs.toString() + " c"
        }
    }

    fun start_calories_counter(){
        configVm.startingConfig()


        macros_goal = Macros(intent.getIntExtra("Calories",0),
            intent.getIntExtra("Protein",0),
            intent.getIntExtra("Fats",0),
            intent.getIntExtra("Carbs",0))

        val defaultIntArray = intArrayOf(0, 0, 0, 0)
        var intArray = intent.getIntArrayExtra("Current_macros") ?: defaultIntArray
        mealMacrosVm.current_macros = Macros(intArray[0],intArray[1],intArray[2],intArray[3])


        intArray = intent.getIntArrayExtra("Meal_macros_1") ?: defaultIntArray
        mealMacrosVm.meal_macros_1 = Macros(intArray[0],intArray[1],intArray[2],intArray[3])
        intArray = intent.getIntArrayExtra("Meal_macros_2") ?: defaultIntArray
        mealMacrosVm.meal_macros_2 = Macros(intArray[0],intArray[1],intArray[2],intArray[3])
        intArray = intent.getIntArrayExtra("Meal_macros_3") ?: defaultIntArray
        mealMacrosVm.meal_macros_3 = Macros(intArray[0],intArray[1],intArray[2],intArray[3])
        intArray = intent.getIntArrayExtra("Meal_macros_4") ?: defaultIntArray
        mealMacrosVm.meal_macros_4 = Macros(intArray[0],intArray[1],intArray[2],intArray[3])
        intArray = intent.getIntArrayExtra("Meal_macros_5") ?: defaultIntArray
        mealMacrosVm.meal_macros_5 = Macros(intArray[0],intArray[1],intArray[2],intArray[3])

        show_meals()


        binding.ShowPoints.text = points.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalorieCounterBinding.inflate(layoutInflater)

        start_calories_counter()

        binding.ChangeConfiguration.setOnClickListener{
            val intent = Intent(this, FirstConfigurationActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonUpdateWeight.setOnClickListener {

        }


        setContentView(binding.root)
    }
}