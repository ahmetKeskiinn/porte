package com.ahmetkeskin.porte.feature.detail.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmetkeskin.porte.R
import com.ahmetkeskin.porte.feature.detail.data.response.WeatherItem
import com.bumptech.glide.Glide

class WeatherAdapter(
) : ListAdapter<WeatherItem, WeatherAdapter.WeatherHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_weather,
            parent,
            false
        )
        return WeatherHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        with(getItem(position)) {
            holder.weatherDescription.text = this.description
            holder.weatherName.text = this.main
            Glide.with(holder.icon).load(
                "https://openweathermap.org/img/wn/$icon@2x.png"
            ).into(holder.icon)
        }
    }

    inner class WeatherHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val icon: ImageView = iv.findViewById(R.id.weatherItemIcon)
        val weatherName: TextView = iv.findViewById(R.id.weatherName)
        val weatherDescription: TextView = iv.findViewById(R.id.weatherDescription)
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<WeatherItem>() {
    override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: WeatherItem,
        newItem: WeatherItem
    ): Boolean {
        return oldItem == newItem
    }
}
