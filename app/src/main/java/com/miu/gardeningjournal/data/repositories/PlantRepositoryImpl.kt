package com.miu.gardeningjournal.data.repositories

import androidx.lifecycle.LiveData
import com.miu.gardeningjournal.data.model.Plant
import com.roman.gardeningjournal.data.PlantDao

class PlantRepositoryImpl(private val plantDao: PlantDao) : PlantRepository {

    override suspend fun insert(plant: Plant) {
        plantDao.insert(plant)
    }

    override suspend fun update(plant: Plant) {
        plantDao.update(plant)
    }

    override suspend fun delete(plantId: Int) {
        plantDao.delete(plantId)
    }

    override fun getPlantById(plantId: Int): LiveData<Plant> {
        return plantDao.getPlantById(plantId)
    }

    override fun getAllPlants(): LiveData<List<Plant>> {
        return plantDao.getAllPlants()
    }
}