package com.miu.gardeningjournal.presentation.plant_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.miu.gardeningjournal.data.model.Plant
import com.miu.gardeningjournal.databinding.FragmentPlantDetailsBinding

class PlantDetailsFragment : Fragment() {

    private lateinit var b: FragmentPlantDetailsBinding
    private lateinit var viewModel: PlantDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentPlantDetailsBinding.inflate(layoutInflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PlantDetailsViewModel::class.java]

        val plantId = arguments?.getInt("id") ?: 0

        viewModel.getPlantById(plantId).observe(viewLifecycleOwner) { plant ->
            plant?.let { displayPlantDetails(it) }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayPlantDetails(plant: Plant) {
        b.tvPlantName.text = plant.name
        b.tvPlantType.text = "Type: ${plant.type}"
        b.tvPlantWater.text = "Watering ${plant.wateringFrequency} days"
        b.tvPlantDate.text = "Planting Date: ${plant.plantingDate}"
    }
}