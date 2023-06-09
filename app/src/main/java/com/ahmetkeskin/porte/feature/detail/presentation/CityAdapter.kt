package com.ahmetkeskin.porte.feature.detail.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmetkeskin.porte.R
import com.ahmetkeskin.porte.feature.detail.data.response.city.City


class CityAdapter(
    val listener: CityClickListener
) : ListAdapter<City, CityAdapter.CityHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_city,
            parent,
            false
        )
        return CityHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        with(getItem(position)) {
            holder.cityName.text = this.name
            holder.itemView.setOnClickListener {
                listener.onCityClicked(this)
            }
        }
    }

    inner class CityHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val cityName: TextView = iv.findViewById(R.id.cityName)
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: City,
        newItem: City
    ): Boolean {
        return oldItem == newItem
    }
}