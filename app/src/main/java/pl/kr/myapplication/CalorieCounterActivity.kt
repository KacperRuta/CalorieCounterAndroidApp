package pl.kr.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.FirstConfiguration.ConfigurationViewModel
import pl.kr.myapplication.databinding.ActivityCalorieCounterBinding

class CalorieCounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalorieCounterBinding
    private val configVm by viewModels<ConfigurationViewModel>()

    var calories_goal: Int = 0
    var protein_goal: Int = 0
    var fats_goal: Int = 0
    var carbs_goal: Int = 0

    var current_calories: Int = 0
    var current_protein: Int = 0
    var current_fats: Int = 0
    var current_carbs: Int = 0


    var points: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalorieCounterBinding.inflate(layoutInflater)

        configVm.startingConfig()

        calories_goal = intent.getIntExtra("Calories",0)
        protein_goal = intent.getIntExtra("Protein",0)
        fats_goal = intent.getIntExtra("Fats",0)
        carbs_goal = intent.getIntExtra("Carbs",0)

        binding.ShowCalories.text = "Calories:    " + current_calories.toString() + "  /  " + calories_goal.toString()
        binding.ShowProtein.text = current_protein.toString() + " / " + protein_goal.toString()
        binding.ShowFats.text = current_fats.toString() + " / " + fats_goal.toString()
        binding.ShowCarbs.text = current_fats.toString() + " / " + carbs_goal.toString()
        binding.ShowPoints.text = points.toString()

        setContentView(binding.root)
    }
}