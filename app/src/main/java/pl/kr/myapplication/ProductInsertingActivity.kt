package pl.kr.myapplication

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.Structures.Macros
import pl.kr.myapplication.ViewModels.ConfigurationViewModel
import pl.kr.myapplication.ViewModels.MealViewModel
import pl.kr.myapplication.ViewModels.ProductsViewModel
import pl.kr.myapplication.databinding.ActivityProductInsertingBinding

class ProductInsertingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductInsertingBinding
    private val mealMacrosVm by viewModels<MealViewModel>()
    private val productsVm by viewModels<ProductsViewModel>()
    private val configVm by viewModels<ConfigurationViewModel>()

    var current_meal_macros: Macros = Macros(0,0,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductInsertingBinding.inflate(layoutInflater)

        productsVm.starting_All_Meals()

        val meal_number = intent.getIntExtra("MealNumber",0)
        var int_array = intent.getIntArrayExtra("Meal_macros") ?: intArrayOf(0,0,0,0)
        current_meal_macros = Macros(int_array[0],int_array[1],int_array[2],int_array[3])
        if(intent.getStringExtra("Meal_name") != ""){
            binding.enterName.setText(intent.getStringExtra("Meal_name"))
        }
        val help_array = intent.getIntArrayExtra("Saved_macros") ?: intArrayOf(0,0,0,0)
        if(help_array[0] != 0){
            binding.enterCalories.setText(help_array[0].toString())
            binding.enterProtein.setText(help_array[1].toString())
            binding.enterFats.setText(help_array[2].toString())
            binding.enterCarbs.setText(help_array[3].toString())
        }


        binding.buttonCancel.setOnClickListener{
            val explicitIntent = Intent(applicationContext, ProductAddingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }

        binding.buttonSaveProduct.setOnClickListener {
            if(binding.enterCalories.text.toString().isEmpty() || binding.enterCalories.text.toString().toInt() > 900 || binding.enterCalories.text.toString().toInt() <= 0){
                binding.enterCalories.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
            if(binding.enterProtein.text.toString().isEmpty() || binding.enterProtein.text.toString().toInt() > 100 || binding.enterProtein.text.toString().toInt() < 0 ){
                binding.enterProtein.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
            if(binding.enterFats.text.toString().isEmpty() || binding.enterFats.text.toString().toInt() > 100 || binding.enterFats.text.toString().toInt() < 0){
                binding.enterFats.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
            if(binding.enterCarbs.text.toString().isEmpty() || binding.enterCarbs.text.toString().toInt() > 100 || binding.enterCarbs.text.toString().toInt() < 0){
                binding.enterCarbs.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
            if(binding.enterName.text.toString().isEmpty()){
                binding.enterCarbs.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
            if(binding.enterPortion.text.toString().isEmpty() ||  binding.enterPortion.text.toString().toInt() <= 0 || binding.enterPortion.text.toString().toInt() <= 0){
                binding.enterPortion.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }


            if(binding.enterCalories.text.toString().isNotEmpty() && binding.enterCalories.text.toString().toInt() in 1..900 &&
                binding.enterProtein.text.toString().isNotEmpty() && binding.enterProtein.text.toString().toInt() in 0..100 &&
                binding.enterFats.text.toString().isNotEmpty() && binding.enterFats.text.toString().toInt() in 0..100 &&
                binding.enterCarbs.text.toString().isNotEmpty() && binding.enterCarbs.text.toString().toInt() in 0..100 &&
                binding.enterName.text.toString().isNotEmpty() &&
                binding.enterPortion.text.toString().isNotEmpty() && binding.enterPortion.text.toString().toInt() > 0){
                productsVm.product_name = binding.enterName.text.toString()
                productsVm.calories = binding.enterCalories.text.toString().toInt()
                productsVm.protein = binding.enterProtein.text.toString().toInt()
                productsVm.fats = binding.enterFats.text.toString().toInt()
                productsVm.carbs = binding.enterCarbs.text.toString().toInt()
                productsVm.add_product()
                val calories = binding.enterCalories.text.toString().toInt() * binding.enterPortion.text.toString().toInt()  * 0.01
                val protein = binding.enterProtein.text.toString().toInt() * binding.enterPortion.text.toString().toInt()  * 0.01
                val fats = binding.enterFats.text.toString().toInt() * binding.enterPortion.text.toString().toInt()  * 0.01
                val carbs = binding.enterCarbs.text.toString().toInt() * binding.enterPortion.text.toString().toInt()  * 0.01
                current_meal_macros = Macros(calories.toInt(),protein.toInt(),fats.toInt(),carbs.toInt())



                val explicitIntent = Intent(applicationContext, ProductAddingActivity::class.java)

                var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
                explicitIntent.putExtra("Meal_macros",intArray)
                explicitIntent.putExtra("MealNumber",meal_number)
                startActivity(explicitIntent)
                finish()
            }

        }



        setContentView(binding.root)
    }
}