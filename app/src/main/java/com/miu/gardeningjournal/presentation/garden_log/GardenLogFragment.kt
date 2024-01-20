package com.miu.gardeningjournal.presentation.garden_log

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.gardeningjournal.R
import com.miu.gardeningjournal.data.model.Plant
import com.miu.gardeningjournal.databinding.FragmentGardenLogBinding
import com.miu.gardeningjournal.listeners.RecyclerItemClickListener

class GardenLogFragment : Fragment() {

    private lateinit var b: FragmentGardenLogBinding
    private lateinit var viewModel: GardenLogViewModel
    private lateinit var adapter: GardenLogAdapter
    private val plants = mutableListOf<Plant>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentGardenLogBinding.inflate(layoutInflater, container, false)
        return b.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[GardenLogViewModel::class.java]

        adapter = GardenLogAdapter(plants)

        adapter.OnItemClickListener(object : RecyclerItemClickListener {
            override fun OnItemClicked(pos: Int) {
                val bundle = bundleOf("id" to plants[pos].id)
                findNavController().navigate(
                    R.id.action_gardenLogFragment_to_plantDetailsFragment,
                    bundle
                )
            }
        })

        b.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        b.recyclerView.adapter = adapter

        viewModel.plantList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) addDummyData()
            plants.clear()
            plants.addAll(it)
            adapter.notifyDataSetChanged()
        }

        b.fabAddPlant.setOnClickListener { findNavController().navigate(R.id.action_gardenLogFragment_to_addPlantFragment) }
    }

    private fun addDummyData() {
        val samplePlants = mutableListOf<Plant>()
        samplePlants.add(Plant(0, "Rose", "Flower", 2, "2023-01-01"))
        samplePlants.add(Plant(0, "Tomato", "Vegetable", 3, "2023-02-15"))
        samplePlants.add(Plant(0, "Basil", "Herb", 1, "2023-03-10"))

        for (plant in samplePlants) {
            viewModel.insert(plant)
        }
    }
}