package com.ahmetkeskin.porte.feature.detail.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetkeskin.porte.BaseFragment
import com.ahmetkeskin.porte.R
import com.ahmetkeskin.porte.base.getJsonDataFromAsset
import com.ahmetkeskin.porte.databinding.FragmentDetailBinding
import com.ahmetkeskin.porte.feature.detail.data.response.WeatherResponse
import com.ahmetkeskin.porte.feature.detail.data.response.city.City
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    layoutId = R.layout.fragment_detail
), LocationListener {

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    private lateinit var cityAdapter: CityAdapter
    private var cityList = arrayListOf<City>()

    override fun onInitDataBinding() {
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        binding.vieModel = viewModel
        initClickListener()
        setTextWatcherOnEditText()
        initRecycler()
        initUI()
    }

    private fun initUI() {
        binding.appName.isFocusable = true
        binding.weatherCity.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                manageVisibilityCitySearch(true)
                manageVisibilityWeather(false)
            }
        }
    }

    private fun manageVisibilityCitySearch(visibility: Boolean) {
        binding.cityList.isVisible = visibility
        binding.cancel.isVisible = visibility
    }

    private fun manageVisibilityWeather(visibility: Boolean) {
        binding.weatherIcon.isVisible = visibility
        binding.city.isVisible = visibility
        binding.celcius.isVisible = visibility
        binding.weatherRv.isVisible = visibility
    }

    private fun getNearestCityModel() = City(id = -1, "Nearest City")
    private fun initCityListFromJson(): ArrayList<City> {
        val list = arrayListOf<City>()
        list.add(getNearestCityModel())
        val jsonFileString = activity?.let { getJsonDataFromAsset(it, R.raw.city_list) }
        val gson = Gson()
        val listPersonType = object : TypeToken<List<City>>() {}.type

        val cities: List<City> = gson.fromJson(jsonFileString, listPersonType)
        cities.forEach {
            list.add(it)
        }
        cityList = list
        return list
    }

    fun View.hideSoftInput() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun initRecycler() {
        cityAdapter = CityAdapter(object : CityClickListener {
            override fun onCityClicked(city: City) {
                manageVisibilityWeather(true)
                manageVisibilityCitySearch(false)
                binding.weatherCity.hideSoftInput()
                binding.weatherCity.clearFocus()
                if (city.name == "Nearest City") {
                    getCurrentLocationWeather()
                } else {
                    getWeather(city.coord?.lat ?: 0.0, city.coord?.lon ?: 0.0)
                }
            }

        })
        binding.cityList.layoutManager = LinearLayoutManager(context)
        binding.cityList.adapter = cityAdapter
        cityAdapter.submitList(initCityListFromJson())
    }

    private fun getCurrentLocationWeather() {
        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((activity?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED)) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    locationPermissionCode
                )
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5f, this)

    }

    private fun setTextWatcherOnEditText() {
        binding.weatherCity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length >= 3) {
                    modifyCityList(s.toString())
                } else {
                    setListOnAdapter(cityList)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun setListOnAdapter(list: ArrayList<City>) {
        cityAdapter.submitList(null)
        cityAdapter.submitList(list)
        cityAdapter.notifyDataSetChanged()
    }

    private fun modifyCityList(city: String) {
        val newCityList = arrayListOf<City>()
        newCityList.add(getNearestCityModel())
        cityList.forEach {
            if (it.name.lowercase().contains(city.lowercase())) {
                newCityList.add(it)
            }
        }
        setListOnAdapter(newCityList)
    }

    private fun initClickListener() {
        binding.back.setOnClickListener { goBack() }
        binding.cancel.setOnClickListener {
            manageVisibilityWeather(true)
            binding.weatherCity.clearFocus()
            binding.weatherCity.hideSoftInput()
            manageVisibilityCitySearch(false)
        }
    }

    private fun goBack() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_detailFragment_to_homeFragment)
    }

    override fun onLocationChanged(location: Location) {
        getWeather(location.latitude, location.longitude)
        locationManager.removeUpdates(this)
    }

    private fun getWeather(latitude: Double, longitude: Double) {
        viewModel.getWeather(lat = latitude, lon = longitude)
            ?.observe(viewLifecycleOwner, Observer {
                initUIForWeather(it)
            })
    }

    private fun initUIForWeather(weatherResponse: WeatherResponse) {
        val url =
            "https://openweathermap.org/img/wn/" + weatherResponse.weather?.get(0)?.icon + "@2x.png"
        Glide.with(binding.celcius).load(url).into(binding.weatherIcon)
        binding.run {
            city.text = "${weatherResponse.name},${weatherResponse.Sys?.country}"
            celcius.text = (weatherResponse.main?.temp?.minus(273.15))?.roundToInt().toString()
        }
    }
}