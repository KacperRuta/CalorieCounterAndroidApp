package pl.kr.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.kr.myapplication.Structures.Macros
import pl.kr.myapplication.ViewModels.ConfigurationViewModel
import pl.kr.myapplication.ViewModels.MealViewModel
import pl.kr.myapplication.ViewModels.ProductsViewModel
import pl.kr.myapplication.databinding.ActivityMealDetailsAddingBinding

class ProductAddingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailsAddingBinding
    private val mealMacrosVm by viewModels<MealViewModel>()
    private val productsVm by viewModels<ProductsViewModel>()
    private val configVm by viewModels<ConfigurationViewModel>()

    var current_meal_macros: Macros = Macros(0,0,0,0)

    fun show_macros(){
        binding.ShowCurrentMealCalories.text = "Calories:   " + current_meal_macros.calories.toString() + " kcal"
        binding.ShowCurrentMealProtein.text = current_meal_macros.protein.toString() + " p"
        binding.ShowCurrentMealFats.text = current_meal_macros.fats.toString() + " f"
        binding.ShowCurrentMealCarbs.text = current_meal_macros.carbs.toString() + " c"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailsAddingBinding.inflate(layoutInflater)

        mealMacrosVm.startingConfig()
        productsVm.starting_All_Meals()
        configVm.startingConfig()

        val meal_number = intent.getIntExtra("MealNumber",0)
        var int_array = intent.getIntArrayExtra("Meal_macros") ?: intArrayOf(0,0,0,0)
        current_meal_macros = Macros(int_array[0],int_array[1],int_array[2],int_array[3])
        show_macros()

        binding.buttonSaveMeal.setOnClickListener {
            if(meal_number == 1) {mealMacrosVm.meal_macros_1 = current_meal_macros}
            else if(meal_number == 2) {mealMacrosVm.meal_macros_2 = current_meal_macros}
            else if(meal_number == 3) {mealMacrosVm.meal_macros_3 = current_meal_macros}
            else if(meal_number == 4) {mealMacrosVm.meal_macros_4 = current_meal_macros}
            else if(meal_number == 5) {mealMacrosVm.meal_macros_5 = current_meal_macros}
            mealMacrosVm.updateConfig(meal_number,current_meal_macros)

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

        binding.enterProductName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                productsVm.show_meals(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        binding.buttonSearchForProduct.setOnClickListener {

            val size = productsVm.meals_to_show.size
            if (size == 0){
                binding.TextProduct1.visibility = View.INVISIBLE
                binding.TextProduct1.isClickable = false
                binding.TextProduct2.visibility = View.INVISIBLE
                binding.TextProduct2.isClickable = false
                binding.TextProduct3.visibility = View.INVISIBLE
                binding.TextProduct3.isClickable = false
                binding.TextProduct4.visibility = View.INVISIBLE
                binding.TextProduct4.isClickable = false
                binding.TextProduct5.visibility = View.INVISIBLE
                binding.TextProduct5.isClickable = false
                binding.TextProduct6.visibility = View.INVISIBLE
                binding.TextProduct6.isClickable = false
                binding.TextProduct7.visibility = View.INVISIBLE
                binding.TextProduct7.isClickable = false
                binding.TextProduct8.visibility = View.INVISIBLE
                binding.TextProduct8.isClickable = false
                binding.TextProduct9.visibility = View.INVISIBLE
                binding.TextProduct9.isClickable = false

            }
            if(size >= 1) {
                binding.TextProduct1.visibility = View.VISIBLE
                binding.TextProduct1.isClickable = true
                binding.TextProduct1.text = productsVm.meals_to_show[0].meal_name + "     " +
                        productsVm.meals_to_show[0].calories.toString() + " kcal" + "   " +
                        productsVm.meals_to_show[0].protein.toString() + " p" + "  " +
                        productsVm.meals_to_show[0].fats.toString() + " f" + "  " +
                        productsVm.meals_to_show[0].carbs.toString() + " c"
                binding.TextProduct2.visibility = View.INVISIBLE
                binding.TextProduct2.isClickable = false
                binding.TextProduct3.visibility = View.INVISIBLE
                binding.TextProduct3.isClickable = false
                binding.TextProduct4.visibility = View.INVISIBLE
                binding.TextProduct4.isClickable = false
                binding.TextProduct5.visibility = View.INVISIBLE
                binding.TextProduct5.isClickable = false
                binding.TextProduct6.visibility = View.INVISIBLE
                binding.TextProduct6.isClickable = false
                binding.TextProduct7.visibility = View.INVISIBLE
                binding.TextProduct7.isClickable = false
                binding.TextProduct8.visibility = View.INVISIBLE
                binding.TextProduct8.isClickable = false
                binding.TextProduct9.visibility = View.INVISIBLE
                binding.TextProduct9.isClickable = false
            }
            if(size >= 2){
                binding.TextProduct2.visibility = View.VISIBLE
                binding.TextProduct2.isClickable = true
                binding.TextProduct2.text = productsVm.meals_to_show[1].meal_name + "     " +
                        productsVm.meals_to_show[1].calories.toString() + " kcal" + "   " +
                        productsVm.meals_to_show[1].protein.toString() + " p" + "  " +
                        productsVm.meals_to_show[1].fats.toString() + " f" + "  " +
                        productsVm.meals_to_show[1].carbs.toString() + " c"
            }
            if(size >= 3){
                binding.TextProduct3.visibility = View.VISIBLE
                binding.TextProduct3.isClickable = true
                binding.TextProduct3.text = productsVm.meals_to_show[2].meal_name + "     " +
                        productsVm.meals_to_show[2].calories.toString() + " kcal" + "   " +
                        productsVm.meals_to_show[2].protein.toString() + " p" + "  " +
                        productsVm.meals_to_show[2].fats.toString() + " f" + "  " +
                        productsVm.meals_to_show[2].carbs.toString() + " c"
            }
            if(size >= 4){
                binding.TextProduct4.visibility = View.VISIBLE
                binding.TextProduct4.isClickable = true
                binding.TextProduct4.text = productsVm.meals_to_show[3].meal_name + "     " +
                        productsVm.meals_to_show[3].calories.toString() + " kcal" + "   " +
                        productsVm.meals_to_show[3].protein.toString() + " p" + "  " +
                        productsVm.meals_to_show[3].fats.toString() + " f" + "  " +
                        productsVm.meals_to_show[3].carbs.toString() + " c"
            }
            if(size >= 5){
                binding.TextProduct5.visibility = View.VISIBLE
                binding.TextProduct5.isClickable = true
                binding.TextProduct5.text = productsVm.meals_to_show[4].meal_name + "     " +
                        productsVm.meals_to_show[4].calories.toString() + " kcal" + "   " +
                        productsVm.meals_to_show[4].protein.toString() + " p" + "  " +
                        productsVm.meals_to_show[4].fats.toString() + " f" + "  " +
                        productsVm.meals_to_show[4].carbs.toString() + " c"
            }
            if(size >= 6){
                binding.TextProduct6.visibility = View.VISIBLE
                binding.TextProduct6.isClickable = true
                binding.TextProduct6.text = productsVm.meals_to_show[5].meal_name + "     " +
                        productsVm.meals_to_show[5].calories.toString() + " kcal" + "   " +
                        productsVm.meals_to_show[5].protein.toString() + " p" + "  " +
                        productsVm.meals_to_show[5].fats.toString() + " f" + "  " +
                        productsVm.meals_to_show[5].carbs.toString() + " c"
            }
            if(size >= 7){
                binding.TextProduct7.visibility = View.VISIBLE
                binding.TextProduct7.isClickable = true
                binding.TextProduct7.text = productsVm.meals_to_show[6].meal_name + "     " +
                        productsVm.meals_to_show[6].calories.toString() + " kcal" + "   " +
                        productsVm.meals_to_show[6].protein.toString() + " p" + "  " +
                        productsVm.meals_to_show[6].fats.toString() + " f" + "  " +
                        productsVm.meals_to_show[6].carbs.toString() + " c"
            }
            if(size >= 8){
                binding.TextProduct8.visibility = View.VISIBLE
                binding.TextProduct8.isClickable = true
                binding.TextProduct8.text = productsVm.meals_to_show[7].meal_name + "     " +
                        productsVm.meals_to_show[7].calories.toString() + " kcal" + "   " +
                        productsVm.meals_to_show[7].protein.toString() + " p" + "  " +
                        productsVm.meals_to_show[7].fats.toString() + " f" + "  " +
                        productsVm.meals_to_show[7].carbs.toString() + " c"
            }
            if(size == 9){
                binding.TextProduct9.visibility = View.VISIBLE
                binding.TextProduct9.isClickable = true
                binding.TextProduct9.text = productsVm.meals_to_show[8].meal_name + "     " +
                        productsVm.meals_to_show[8].calories.toString() + " kcal" + "   " +
                        productsVm.meals_to_show[8].protein.toString() + " p" + "  " +
                        productsVm.meals_to_show[8].fats.toString() + " f" + "  " +
                        productsVm.meals_to_show[8].carbs.toString() + " c"
            }

        }


        binding.buttonEnterProduct.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductInsertingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }

        binding.TextProduct1.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductInsertingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            intArray = intArrayOf(productsVm.meals_to_show[0].calories, productsVm.meals_to_show[0].protein, productsVm.meals_to_show[0].fats , productsVm.meals_to_show[0].carbs)
            explicitIntent.putExtra("Meal_name",productsVm.meals_to_show[0].meal_name)
            explicitIntent.putExtra("Saved_macros", intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }
        binding.TextProduct2.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductInsertingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            intArray = intArrayOf(productsVm.meals_to_show[1].calories, productsVm.meals_to_show[1].protein, productsVm.meals_to_show[1].fats , productsVm.meals_to_show[1].carbs)
            explicitIntent.putExtra("Meal_name",productsVm.meals_to_show[1].meal_name)
            explicitIntent.putExtra("Saved_macros", intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }
        binding.TextProduct3.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductInsertingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            intArray = intArrayOf(productsVm.meals_to_show[2].calories, productsVm.meals_to_show[2].protein, productsVm.meals_to_show[2].fats , productsVm.meals_to_show[2].carbs)
            explicitIntent.putExtra("Meal_name",productsVm.meals_to_show[2].meal_name)
            explicitIntent.putExtra("Saved_macros", intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }
        binding.TextProduct4.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductInsertingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            intArray = intArrayOf(productsVm.meals_to_show[3].calories, productsVm.meals_to_show[3].protein, productsVm.meals_to_show[3].fats , productsVm.meals_to_show[3].carbs)
            explicitIntent.putExtra("Meal_name",productsVm.meals_to_show[3].meal_name)
            explicitIntent.putExtra("Saved_macros", intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }
        binding.TextProduct5.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductInsertingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            intArray = intArrayOf(productsVm.meals_to_show[4].calories, productsVm.meals_to_show[4].protein, productsVm.meals_to_show[4].fats , productsVm.meals_to_show[4].carbs)
            explicitIntent.putExtra("Meal_name",productsVm.meals_to_show[4].meal_name)
            explicitIntent.putExtra("Saved_macros", intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }
        binding.TextProduct6.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductInsertingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            intArray = intArrayOf(productsVm.meals_to_show[5].calories, productsVm.meals_to_show[5].protein, productsVm.meals_to_show[5].fats , productsVm.meals_to_show[5].carbs)
            explicitIntent.putExtra("Meal_name",productsVm.meals_to_show[5].meal_name)
            explicitIntent.putExtra("Saved_macros", intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }
        binding.TextProduct7.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductInsertingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            intArray = intArrayOf(productsVm.meals_to_show[6].calories, productsVm.meals_to_show[6].protein, productsVm.meals_to_show[6].fats , productsVm.meals_to_show[6].carbs)
            explicitIntent.putExtra("Meal_name",productsVm.meals_to_show[6].meal_name)
            explicitIntent.putExtra("Saved_macros", intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }
        binding.TextProduct8.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductInsertingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            intArray = intArrayOf(productsVm.meals_to_show[7].calories, productsVm.meals_to_show[7].protein, productsVm.meals_to_show[7].fats , productsVm.meals_to_show[7].carbs)
            explicitIntent.putExtra("Meal_name",productsVm.meals_to_show[7].meal_name)
            explicitIntent.putExtra("Saved_macros", intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }
        binding.TextProduct9.setOnClickListener {
            val explicitIntent = Intent(applicationContext, ProductInsertingActivity::class.java)

            var intArray = intArrayOf(current_meal_macros.calories, current_meal_macros.protein, current_meal_macros.fats, current_meal_macros.carbs)
            explicitIntent.putExtra("Meal_macros",intArray)
            intArray = intArrayOf(productsVm.meals_to_show[8].calories, productsVm.meals_to_show[8].protein, productsVm.meals_to_show[8].fats , productsVm.meals_to_show[8].carbs)
            explicitIntent.putExtra("Meal_name",productsVm.meals_to_show[8].meal_name)
            explicitIntent.putExtra("Saved_macros", intArray)
            explicitIntent.putExtra("MealNumber",meal_number)
            startActivity(explicitIntent)
            finish()
        }



        setContentView(binding.root)
    }
}