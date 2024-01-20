package com.miu.gardeningjournal.presentation.garden_log

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.gardeningjournal.data.model.Plant
import com.miu.gardeningjournal.data.repositories.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GardenLogViewModel @Inject constructor(private val repo: PlantRepository) : ViewModel() {

    val plantList: LiveData<List<Plant>> = repo.getAllPlants()

    fun insert(plant: Plant) = viewModelScope.launch {
        repo.insert(plant)
    }

    fun delete(plantId: Int) = viewModelScope.launch {
        repo.delete(plantId)
    }
}