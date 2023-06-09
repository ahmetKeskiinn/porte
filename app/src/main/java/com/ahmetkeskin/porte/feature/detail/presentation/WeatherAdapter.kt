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
            //holder.celciusMax = this.
        }
    }

    inner class WeatherHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val icon: ImageView = iv.findViewById(R.id.weatherItemIcon)
        val celciusMax: TextView = iv.findViewById(R.id.celciusMax)
        val celciusMin: TextView = iv.findViewById(R.id.celciusMin)
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
