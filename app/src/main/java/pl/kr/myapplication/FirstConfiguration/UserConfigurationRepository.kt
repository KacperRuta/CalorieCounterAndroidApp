package pl.kr.myapplication.FirstConfiguration

import android.content.Context
import pl.kr.myapplication.FirstConfiguration.userConfigurationData.ConfigurationDb
import pl.kr.myapplication.FirstConfiguration.userConfigurationData.UserConfiguration
import pl.kr.myapplication.FirstConfiguration.userConfigurationData.UserConfigurationDao

class UserConfigurationRepository(context: Context): UserConfigurationDao {

    private val configDao = ConfigurationDb.getInstance(context).configurationDao()


    override suspend fun insertOrUpdate(userConfiguration: UserConfiguration){
        configDao.insertOrUpdate(userConfiguration)
    }


    override suspend fun isConfigurated(): Boolean {
        return configDao.isConfigurated()
    }

    override suspend fun getAge(): Int {
        return configDao.getAge()
    }

    override suspend fun getHeight(): Int {
        return configDao.getHeight()
    }

    override suspend fun getWeight(): Double {
        return configDao.getWeight()
    }

    override suspend fun getGender(): Int {
        return configDao.getGender()
    }

    override suspend fun getActivityLevel(): Int {
        return configDao.getActivityLevel()
    }

    override suspend fun getWeightGoal(): Double {
        return configDao.getWeightGoal()
    }

    override suspend fun getCalories(): Int {
        return configDao.getCalories()
    }

    override suspend fun getProtein(): Int {
        return configDao.getProtein()

    }

    override suspend fun getFats(): Int {
        return configDao.getFats()

    }

    override suspend fun getCarbs(): Int {
        return configDao.getCarbs()

    }

    override suspend fun getDietChoice(): Int {
        return configDao.getDietChoice()
    }
}
