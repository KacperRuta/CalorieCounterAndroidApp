package pl.kr.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.FirstConfiguration.ConfigurationViewModel
import pl.kr.myapplication.FirstConfiguration.FirstConfigurationActivity
import pl.kr.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val configVm by viewModels<ConfigurationViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        configVm.startingConfig()



        binding.mainButton.setOnClickListener {
            if (configVm.configurated == false) {
                val explicitIntent = Intent(applicationContext, FirstConfigurationActivity::class.java)
                startActivity(explicitIntent)
            } else {
                val explicitIntent = Intent(applicationContext, CalorieCounterActivity::class.java)
                explicitIntent.putExtra("Calories",configVm.calories)
                explicitIntent.putExtra("Protein",configVm.protein)
                explicitIntent.putExtra("Fats",configVm.fats)
                explicitIntent.putExtra("Carbs",configVm.carbs)
                startActivity(explicitIntent)
            }
        }




        setContentView(binding.root)
    }
}