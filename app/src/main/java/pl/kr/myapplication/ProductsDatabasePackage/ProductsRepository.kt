package pl.kr.myapplication.ProductsDatabasePackage

import android.content.Context

class ProductsRepository(context: Context): ProductsDao {

    private val productsDao = ProductsDb.getInstance(context).productsDao()
    override suspend fun insertOrUpdateMeal(meal: Product) {
        productsDao.insertOrUpdateMeal(meal)
    }

    override suspend fun findMeals(word: String): List<Product> {
        return productsDao.findMeals(word)
    }

    override suspend fun getAllProducts(): List<Product> {
        return productsDao.getAllProducts()
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