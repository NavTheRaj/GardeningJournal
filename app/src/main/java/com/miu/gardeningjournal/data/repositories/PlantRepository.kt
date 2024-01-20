package com.miu.gardeningjournal.data.repositories

import androidx.lifecycle.LiveData
import com.miu.gardeningjournal.data.model.Plant

interface PlantRepository {

    suspend fun insert(plant: Plant)

    suspend fun update(plant: Plant)

    suspend fun delete(plantId: Int)

    fun getPlantById(plantId: Int): LiveData<Plant>

    fun getAllPlants() : LiveData<List<Plant>>
}