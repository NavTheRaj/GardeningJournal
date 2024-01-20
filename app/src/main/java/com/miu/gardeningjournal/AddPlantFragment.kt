package com.miu.gardeningjournal

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.miu.gardeningjournal.data.model.Plant
import com.miu.gardeningjournal.databinding.FragmentAddPlantBinding
import com.miu.gardeningjournal.presentation.garden_log.GardenLogViewModel
import java.util.Calendar

class AddPlantFragment : Fragment() {

    private lateinit var b: FragmentAddPlantBinding
    private lateinit var viewModel: GardenLogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentAddPlantBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[GardenLogViewModel::class.java]

        b.btnAddPlant.setOnClickListener {
            if (b.etPlantName.text.isEmpty()) {
                showError(b.etPlantName, "Plant Name is required!!")
            } else if (b.etPlantType.text.isEmpty()) {
                showError(b.etPlantType, "Type is required!!")
            } else if (b.etWateringFrequency.text.isEmpty()) {
                showError(b.etWateringFrequency, "Watering Frequency is required!!")
            } else if (b.tvPlantDate.text.isEmpty()) {
                showError(b.tvPlantDate, "Type is required!!")
            } else {
                val plantName = b.etPlantName.text.toString()
                val plantType = b.etPlantType.text.toString()
                val wateringFrequency = b.etWateringFrequency.text.toString().toInt()
                val plantingDate = b.tvPlantDate.text.toString()
                val plant = Plant(0, plantName, plantType, wateringFrequency, plantingDate)
                viewModel.insert(plant)
                clearViews()
            }
        }

        b.tvPlantDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(
                requireContext(),
                { _, sYear, sMonth, sDay -> b.tvPlantDate.text = "$sYear-${sMonth + 1}-$sDay" },
                year,
                month,
                day
            ).show()
        }

    }

    private fun clearViews() {
        b.etPlantName.text.clear()
        b.etPlantType.text.clear()
        b.etWateringFrequency.text.clear()
        b.tvPlantDate.text = ""
        Toast.makeText(requireActivity(), "Plant added to database.", Toast.LENGTH_SHORT).show()
    }

    private fun showError(view: EditText, errorMessage: String) {
        view.error = errorMessage
        view.requestFocus()
    }

    private fun showError(view: TextView, errorMessage: String) {
        view.error = errorMessage
        view.requestFocus()
    }

}