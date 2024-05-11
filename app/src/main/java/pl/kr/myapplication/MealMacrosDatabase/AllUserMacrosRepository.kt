package pl.kr.myapplication.MealMacrosDatabase

import android.content.Context

class AllUserMacrosRepository(context: Context): AllUserMacrosDao {

    private val userMacrosDao = AllUserMacrosDb.getInstance(context).allUserMacrosDao()
    override suspend fun insertOrUpdate(allUserMacros: AllUserMacros) {
        userMacrosDao.insertOrUpdate(allUserMacros)
    }

    override suspend fun setValuesById(
        userId: Int,
        calories: Int,
        protein: Int,
        fats: Int,
        carbs: Int
    ) {
        userMacrosDao.setValuesById(userId,calories,protein,fats,carbs)
    }

    override suspend fun getCalories(mealID: Int): Int {
        return userMacrosDao.getCalories(mealID)
    }

    override suspend fun getProtein(mealID: Int): Int {
        return userMacrosDao.getProtein(mealID)
    }

    override suspend fun getFats(mealID: Int): Int {
        return userMacrosDao.getFats(mealID)
    }

    override suspend fun getCarbs(mealID: Int): Int {
        return userMacrosDao.getCarbs(mealID)
    }

    override suspend fun resetValuesById(userId: Int) {
        userMacrosDao.resetValuesById(userId)
    }


}