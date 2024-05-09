package pl.kr.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.FirstConfiguration.ConfigurationViewModel
import pl.kr.myapplication.databinding.ActivityCalorieCounterBinding

class CalorieCounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalorieCounterBinding
    private val configVm by viewModels<ConfigurationViewModel>()

    var calories: Int = 0
    var protein: Int = 0
    var fats: Int = 0
    var carbs: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalorieCounterBinding.inflate(layoutInflater)

        configVm.startingConfig()

        calories = intent.getIntExtra("Calories",0)
        protein = intent.getIntExtra("Protein",0)
        fats = intent.getIntExtra("Fats",0)
        carbs = intent.getIntExtra("Carbs",0)


        setContentView(binding.root)
    }
}