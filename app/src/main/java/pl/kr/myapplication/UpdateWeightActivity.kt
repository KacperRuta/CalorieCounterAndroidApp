package pl.kr.myapplication

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.ViewModels.ConfigurationViewModel
import pl.kr.myapplication.ViewModels.MealViewModel
import pl.kr.myapplication.databinding.ActivityUpdateWeightBinding
import kotlin.math.roundToInt


class UpdateWeightActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateWeightBinding
    private val configVm by viewModels<ConfigurationViewModel>()
    private val mealMacrosVm by viewModels<MealViewModel>()

    var calories: Int = 0
    var protein: Int = 0
    var fats: Int = 0
    var carbs: Int = 0
    var choice: Int = 0
    var weight: Double = 0.0

    val min_weight = 20.0
    val max_weight = 500.0
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


            h = (c * 0.3) / 9
            fats = h.roundToInt()


            h = (c * 0.55) / 4
            carbs = h.roundToInt()

        }

        // High protein diet:
        if (choice == 2){
            val c = calories.toDouble()

            if (configVm.weight_goal < configVm.weight) {
                var h = (c * 0.35) / 4
                protein = h.roundToInt()


                h = (c * 0.2) / 9
                fats = h.roundToInt()


                h = (c * 0.45) / 4
                carbs = h.roundToInt()

            } else {
                var h = (c * 0.3) / 4
                protein = h.roundToInt()

                h = (c * 0.25) / 9
                fats = h.roundToInt()

                h = (c * 0.45) / 4
                carbs = h.roundToInt()
            }
        }


        // For high fat diet the percentage look like:
        if (choice == 3){
            val c = calories.toDouble()

            var h = (c * 0.2) / 4
            protein = h.roundToInt()

            h = (c * 0.7) / 9
            fats = h.roundToInt()

            h = (c * 0.1) / 4
            carbs = h.roundToInt()
        }

        configVm.protein = protein
        configVm.fats = fats
        configVm.carbs = carbs
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateWeightBinding.inflate(layoutInflater)
        configVm.startingConfig()
        mealMacrosVm.startingConfig()

        binding.buttonDoNotSave.setOnClickListener {
                val explicitIntent = Intent(applicationContext, CalorieCounterActivity::class.java)
                explicitIntent.putExtra("Calories", configVm.calories)
                explicitIntent.putExtra("Protein", configVm.protein)
                explicitIntent.putExtra("Fats", configVm.fats)
                explicitIntent.putExtra("Carbs", configVm.carbs)

                var intArray = intArrayOf(
                    mealMacrosVm.meal_macros_1.calories,
                    mealMacrosVm.meal_macros_1.protein,
                    mealMacrosVm.meal_macros_1.fats,
                    mealMacrosVm.meal_macros_1.carbs
                )
                explicitIntent.putExtra("Meal_macros_1", intArray)

                intArray = intArrayOf(
                    mealMacrosVm.meal_macros_2.calories,
                    mealMacrosVm.meal_macros_2.protein,
                    mealMacrosVm.meal_macros_2.fats,
                    mealMacrosVm.meal_macros_2.carbs
                )
                explicitIntent.putExtra("Meal_macros_2", intArray)

                intArray = intArrayOf(
                    mealMacrosVm.meal_macros_3.calories,
                    mealMacrosVm.meal_macros_3.protein,
                    mealMacrosVm.meal_macros_3.fats,
                    mealMacrosVm.meal_macros_3.carbs
                )
                explicitIntent.putExtra("Meal_macros_3", intArray)

                intArray = intArrayOf(
                    mealMacrosVm.meal_macros_4.calories,
                    mealMacrosVm.meal_macros_4.protein,
                    mealMacrosVm.meal_macros_4.fats,
                    mealMacrosVm.meal_macros_4.carbs
                )
                explicitIntent.putExtra("Meal_macros_4", intArray)

                intArray = intArrayOf(
                    mealMacrosVm.meal_macros_5.calories,
                    mealMacrosVm.meal_macros_5.protein,
                    mealMacrosVm.meal_macros_5.fats,
                    mealMacrosVm.meal_macros_5.carbs
                )
                explicitIntent.putExtra("Meal_macros_5", intArray)

                startActivity(explicitIntent)
                finish()
            }


        binding.editWeightAfterConfiguration.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Sprawdź, czy wprowadzona liczba mieści się w prawidłowym zakresie
                val text = s.toString()
                if (text.isNotEmpty()) {
                    val enteredWeight = text.toDouble()
                    if (enteredWeight > min_weight && enteredWeight < max_weight) {
                        // Wprowadzona liczba mieści się w zakresie, ustaw kolor na zielony
                        binding.editWeightAfterConfiguration.backgroundTintList = ColorStateList.valueOf(
                            Color.GREEN)
                        weight = enteredWeight
                    } else {
                        // Wprowadzona liczba nie mieści się w zakresie, ustaw kolor na czerwony
                        binding.editWeightAfterConfiguration.backgroundTintList = ColorStateList.valueOf(Color.RED)
                    }
                } else {
                    // Pole tekstowe jest puste, ustaw kolor na czerwony
                    binding.editWeightAfterConfiguration.backgroundTintList = ColorStateList.valueOf(Color.RED)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.buttonSave.setOnClickListener {
            if (weight < min_weight || weight > max_weight) {
                binding.editWeightAfterConfiguration.backgroundTintList = ColorStateList.valueOf(Color.RED)
            } else {
                choice = configVm.diet_choice
                configVm.weight = weight
                calculate_calories()

                calculate_macros()

                configVm.updateConfig()
                val explicitIntent = Intent(applicationContext, CalorieCounterActivity::class.java)
                explicitIntent.putExtra("Calories", configVm.calories)
                explicitIntent.putExtra("Protein", configVm.protein)
                explicitIntent.putExtra("Fats", configVm.fats)
                explicitIntent.putExtra("Carbs", configVm.carbs)

                var intArray = intArrayOf(
                    mealMacrosVm.meal_macros_1.calories,
                    mealMacrosVm.meal_macros_1.protein,
                    mealMacrosVm.meal_macros_1.fats,
                    mealMacrosVm.meal_macros_1.carbs
                )
                explicitIntent.putExtra("Meal_macros_1", intArray)

                intArray = intArrayOf(
                    mealMacrosVm.meal_macros_2.calories,
                    mealMacrosVm.meal_macros_2.protein,
                    mealMacrosVm.meal_macros_2.fats,
                    mealMacrosVm.meal_macros_2.carbs
                )
                explicitIntent.putExtra("Meal_macros_2", intArray)

                intArray = intArrayOf(
                    mealMacrosVm.meal_macros_3.calories,
                    mealMacrosVm.meal_macros_3.protein,
                    mealMacrosVm.meal_macros_3.fats,
                    mealMacrosVm.meal_macros_3.carbs
                )
                explicitIntent.putExtra("Meal_macros_3", intArray)

                intArray = intArrayOf(
                    mealMacrosVm.meal_macros_4.calories,
                    mealMacrosVm.meal_macros_4.protein,
                    mealMacrosVm.meal_macros_4.fats,
                    mealMacrosVm.meal_macros_4.carbs
                )
                explicitIntent.putExtra("Meal_macros_4", intArray)

                intArray = intArrayOf(
                    mealMacrosVm.meal_macros_5.calories,
                    mealMacrosVm.meal_macros_5.protein,
                    mealMacrosVm.meal_macros_5.fats,
                    mealMacrosVm.meal_macros_5.carbs
                )
                explicitIntent.putExtra("Meal_macros_5", intArray)

                startActivity(explicitIntent)
                finish()
            }
        }
        setContentView(binding.root)

        }
    }
