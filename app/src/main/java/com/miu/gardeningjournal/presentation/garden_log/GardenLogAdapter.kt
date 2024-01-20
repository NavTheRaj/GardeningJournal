package com.miu.gardeningjournal.presentation.garden_log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.miu.gardeningjournal.R
import com.miu.gardeningjournal.data.model.Plant
import com.miu.gardeningjournal.listeners.RecyclerItemClickListener

class GardenLogAdapter(private val plants: List<Plant>) :
    RecyclerView.Adapter<GardenLogAdapter.Holder>() {

    private var recyclerItemClickListener: RecyclerItemClickListener? = null

    inner class Holder(view: View) : ViewHolder(view) {
        val tvPlantName: TextView = view.findViewById(R.id.plantName)

        init {
            tvPlantName.setOnClickListener {
                recyclerItemClickListener?.OnItemClicked(layoutPosition)
            }
        }
    }

    fun OnItemClickListener(recyclerItemClickListener: RecyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = plants.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvPlantName.text = plants[position].name
    }
}