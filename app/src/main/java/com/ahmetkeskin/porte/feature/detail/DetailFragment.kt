package com.ahmetkeskin.porte.feature.detail

import androidx.navigation.Navigation
import com.ahmetkeskin.porte.BaseFragment
import com.ahmetkeskin.porte.R
import com.ahmetkeskin.porte.databinding.FragmentDetailBinding
import com.ahmetkeskin.porte.databinding.FragmentHomeBinding
import com.ahmetkeskin.porte.feature.home.presentation.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    layoutId = R.layout.fragment_home
) {
    override fun onInitDataBinding() {

    }
}