package com.ahmetkeskin.porte.feature.home

import com.ahmetkeskin.porte.BaseFragment
import com.ahmetkeskin.porte.R
import com.ahmetkeskin.porte.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    layoutId = R.layout.fragment_home
) {
    override fun onInitDataBinding() {

    }

}