package pl.kr.myapplication.MealDetailsDatabase

import android.content.Context

class ProductsRepository(context: Context): ProductsDao {

    private val productsDao = AllUserMacrosDb.getInstance(context).allUserMacrosDao()
    override suspend fun insertOrUpdateMeal(meal: Products) {
        productsDao.insertOrUpdateMeal(meal)
    }

    override fun findMeals(word: String): List<Products> {
        return productsDao.findMeals(word)
    }

    override suspend fun getCalories(mealID: String): Int {
        return productsDao.getCalories(mealID)
    }

    override suspend fun getProtein(mealID: String): Int {
        return productsDao.getProtein(mealID)

    }

    override suspend fun getFats(mealID: String): Int {
        return productsDao.getFats(mealID)

    }

    override suspend fun getCarbs(mealID: String): Int {
        return productsDao.getCarbs(mealID)

    }

    override suspend fun getMealName(mealID: String): String {
        return productsDao.getMealName(mealID)
    }

}