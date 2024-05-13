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


        binding.ShowMeal1Calories.text = mealMacrosVm.meal_macros_1.calories.toString() + " kcal"
        binding.ShowMeal1Protein.text = mealMacrosVm.meal_macros_1.protein.toString() + " p"
        binding.ShowMeal1Fats.text = mealMacrosVm.meal_macros_1.fats.toString() + " f"
        binding.ShowMeal1Carbs.text = mealMacrosVm.meal_macros_1.carbs.toString() + " c"


        binding.ShowMeal2Calories.text = mealMacrosVm.meal_macros_2.calories.toString() + " kcal"
        binding.ShowMeal2Protein.text = mealMacrosVm.meal_macros_2.protein.toString() + " p"
        binding.ShowMeal2Fats.text = mealMacrosVm.meal_macros_2.fats.toString() + " f"
        binding.ShowMeal2Carbs.text = mealMacrosVm.meal_macros_2.carbs.toString() + " c"


        binding.ShowMeal3Calories.text = mealMacrosVm.meal_macros_3.calories.toString() + " kcal"
        binding.ShowMeal3Protein.text = mealMacrosVm.meal_macros_3.protein.toString() + " p"
        binding.ShowMeal3Fats.text = mealMacrosVm.meal_macros_3.fats.toString() + " f"
        binding.ShowMeal3Carbs.text = mealMacrosVm.meal_macros_3.carbs.toString() + " c"


        binding.ShowMeal4Calories.text = mealMacrosVm.meal_macros_4.calories.toString() + " kcal"
        binding.ShowMeal4Protein.text = mealMacrosVm.meal_macros_4.protein.toString() + " p"
        binding.ShowMeal4Fats.text = mealMacrosVm.meal_macros_4.fats.toString() + " f"
        binding.ShowMeal4Carbs.text = mealMacrosVm.meal_macros_4.carbs.toString() + " c"


        binding.ShowMeal5Calories.text = mealMacrosVm.meal_macros_5.calories.toString() + " kcal"
        binding.ShowMeal5Protein.text = mealMacrosVm.meal_macros_5.protein.toString() + " p"
        binding.ShowMeal5Fats.text = mealMacrosVm.meal_macros_5.fats.toString() + " f"
        binding.ShowMeal5Carbs.text = mealMacrosVm.meal_macros_5.carbs.toString() + " c"

    }

    fun calculate_current_macros(){
        mealMacrosVm.current_macros = Macros(mealMacrosVm.meal_macros_1.calories +
                mealMacrosVm.meal_macros_2.calories +
                mealMacrosVm.meal_macros_3.calories +
                mealMacrosVm.meal_macros_4.calories +
                mealMacrosVm.meal_macros_5.calories,
            mealMacrosVm.meal_macros_1.protein +
                    mealMacrosVm.meal_macros_2.protein +
                    mealMacrosVm.meal_macros_3.protein +
                    mealMacrosVm.meal_macros_4.protein +
                    mealMacrosVm.meal_macros_5.protein,
            mealMacrosVm.meal_macros_1.fats +
                    mealMacrosVm.meal_macros_2.fats +
                    mealMacrosVm.meal_macros_3.fats +
                    mealMacrosVm.meal_macros_4.fats +
                    mealMacrosVm.meal_macros_5.fats,
            mealMacrosVm.meal_macros_1.carbs +
                    mealMacrosVm.meal_macros_2.carbs +
                    mealMacrosVm.meal_macros_3.carbs +
                    mealMacrosVm.meal_macros_4.carbs + mealMacrosVm.meal_macros_5.carbs)
    }
    fun start_calories_counter(){
        configVm.startingConfig()


        macros_goal = Macros(intent.getIntExtra("Calories",0),
            intent.getIntExtra("Protein",0),
            intent.getIntExtra("Fats",0),
            intent.getIntExtra("Carbs",0))

        val defaultIntArray = intArrayOf(0, 0, 0, 0)
        var intArray = intent.getIntArrayExtra("Current_macros") ?: defaultIntArray


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

        calculate_current_macros()
        show_meals()

        points = intent.getIntExtra("Points",0)
        binding.ShowPoints.text = points.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalorieCounterBinding.inflate(layoutInflater)

        start_calories_counter()

        binding.ChangeConfiguration.setOnClickListener{
            val explicitIntent = Intent(applicationContext, FirstConfigurationActivity::class.java)
            startActivity(explicitIntent)
            finish()
        }

        binding.buttonUpdateWeight.setOnClickListener {
            val explicitIntent = Intent(applicationContext, UpdateWeightActivity::class.java)
            startActivity(explicitIntent)
            finish()
        }





        binding.AddMeal1.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductAddingActivity::class.java)
            explicitIntent.putExtra("MealNumber", 1)
            var intArray = intArrayOf(mealMacrosVm.meal_macros_1.calories,
                mealMacrosVm.meal_macros_1.protein,
                mealMacrosVm.meal_macros_1.fats,
                mealMacrosVm.meal_macros_1.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            startActivity(explicitIntent)
            finish()
        }

        binding.DeleteMeal1.setOnClickListener {
            mealMacrosVm.reset_meal_macros(1)
            mealMacrosVm.meal_macros_1 = Macros(0,0,0,0)
            calculate_current_macros()
        }

        binding.AddMeal2.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductAddingActivity::class.java)
            explicitIntent.putExtra("MealNumber", 2)
            var intArray = intArrayOf(mealMacrosVm.meal_macros_2.calories,
                mealMacrosVm.meal_macros_2.protein,
                mealMacrosVm.meal_macros_2.fats,
                mealMacrosVm.meal_macros_2.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            startActivity(explicitIntent)
            finish()
        }

        binding.DeleteMeal2.setOnClickListener {
            mealMacrosVm.reset_meal_macros(2)
            mealMacrosVm.meal_macros_1 = Macros(0,0,0,0)
            calculate_current_macros()
        }

        binding.AddMeal3.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductAddingActivity::class.java)
            explicitIntent.putExtra("MealNumber", 3)
            var intArray = intArrayOf(mealMacrosVm.meal_macros_3.calories,
                mealMacrosVm.meal_macros_3.protein,
                mealMacrosVm.meal_macros_3.fats,
                mealMacrosVm.meal_macros_3.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            startActivity(explicitIntent)
            finish()
        }

        binding.DeleteMeal3.setOnClickListener {
            mealMacrosVm.reset_meal_macros(3)
            mealMacrosVm.meal_macros_3 = Macros(0,0,0,0)
            calculate_current_macros()
        }

        binding.AddMeal4.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductAddingActivity::class.java)
            explicitIntent.putExtra("MealNumber", 4)
            var intArray = intArrayOf(mealMacrosVm.meal_macros_4.calories,
                mealMacrosVm.meal_macros_4.protein,
                mealMacrosVm.meal_macros_4.fats,
                mealMacrosVm.meal_macros_4.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            startActivity(explicitIntent)
            finish()
        }

        binding.DeleteMeal4.setOnClickListener {
            mealMacrosVm.reset_meal_macros(4)
            mealMacrosVm.meal_macros_4 = Macros(0,0,0,0)
            calculate_current_macros()
        }

        binding.AddMeal5.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductAddingActivity::class.java)
            explicitIntent.putExtra("MealNumber", 5)
            var intArray = intArrayOf(mealMacrosVm.meal_macros_5.calories,
                mealMacrosVm.meal_macros_5.protein,
                mealMacrosVm.meal_macros_5.fats,
                mealMacrosVm.meal_macros_5.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            startActivity(explicitIntent)
            finish()
        }

        binding.DeleteMeal1.setOnClickListener {
            mealMacrosVm.reset_meal_macros(1)
            mealMacrosVm.meal_macros_1 = Macros(0,0,0,0)
            calculate_current_macros()
        }

        binding.DeleteMeal2.setOnClickListener {
            mealMacrosVm.meal_macros_2 = Macros(0,0,0,0)
            mealMacrosVm.reset_meal_macros(2)
            calculate_current_macros()
            show_meals()
        }
        binding.DeleteMeal3.setOnClickListener {
            mealMacrosVm.meal_macros_3 = Macros(0,0,0,0)
            mealMacrosVm.reset_meal_macros(3)
            calculate_current_macros()
            show_meals()
        }
        binding.DeleteMeal4.setOnClickListener {
            mealMacrosVm.meal_macros_4 = Macros(0,0,0,0)
            mealMacrosVm.reset_meal_macros(4)
            calculate_current_macros()
            show_meals()
        }
        binding.DeleteMeal5.setOnClickListener {
            mealMacrosVm.meal_macros_5 = Macros(0,0,0,0)
            mealMacrosVm.reset_meal_macros(5)
            calculate_current_macros()
            show_meals()
        }






        binding.endDay.setOnClickListener {
            binding.endDay.text = "ARE U SURE"
            binding.endDay.setOnClickListener {
                // dodaj nowy layout i podsumowanie zrob
            }
        }

        setContentView(binding.root)
    }
}