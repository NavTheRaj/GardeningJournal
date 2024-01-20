package com.miu.gardeningjournal.presentation.plant_details

import androidx.lifecycle.ViewModel
import com.miu.gardeningjournal.data.repositories.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlantDetailsViewModel @Inject constructor(private val repo: PlantRepository) : ViewModel() {

    fun getPlantById(plantId: Int) = repo.getPlantById(plantId)
}