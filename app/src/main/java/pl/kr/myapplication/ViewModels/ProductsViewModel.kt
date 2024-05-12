package pl.kr.myapplication.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pl.kr.myapplication.ProductsDatabasePackage.Product
import pl.kr.myapplication.ProductsDatabasePackage.ProductsRepository

class ProductsViewModel(app: Application): AndroidViewModel(app) {
    private val productsDao = ProductsRepository(app.applicationContext)

    var meals_to_show: List<Product> = List(9) { Product("",0,0,0,0)}
    var all_meals: List<Product> = List(1){Product("",0,0,0,0)}

    var product_name: String = ""
    var calories: Int = 0
    var protein: Int = 0
    var fats: Int = 0
    var carbs: Int = 0

    fun show_meals(word: String){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            if (productsDao.findMeals(word).size <= 9) {
                meals_to_show = productsDao.findMeals(word)
            } else {
                meals_to_show = listOf(
                    productsDao.findMeals(word)[0],
                    productsDao.findMeals(word)[1],
                    productsDao.findMeals(word)[2],
                    productsDao.findMeals(word)[3],
                    productsDao.findMeals(word)[4],
                    productsDao.findMeals(word)[5],
                    productsDao.findMeals(word)[6],
                    productsDao.findMeals(word)[7],
                    productsDao.findMeals(word)[8]
                )
            }
        }
    }

    fun add_product(){
        if (product_name != "") {
            val product = Product(product_name,calories,protein,fats,carbs)

            CoroutineScope(viewModelScope.coroutineContext).launch{
                productsDao.insertOrUpdateMeal(product)
            }
        }
    }

    fun get_meal_macros(word: String){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            calories = productsDao.getCalories(word)
            protein = productsDao.getProtein(word)
            fats = productsDao.getFats(word)
            carbs = productsDao.getCarbs(word)
        }
    }

    fun starting_All_Meals(){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            all_meals = productsDao.getAllProducts()
        }
    }

}