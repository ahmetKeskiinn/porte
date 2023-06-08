package com.ahmetkeskin.porte.feature.detail.presentation

import com.ahmetkeskin.porte.BaseFragment
import com.ahmetkeskin.porte.R
import com.ahmetkeskin.porte.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    layoutId = R.layout.fragment_home
) {
    override fun onInitDataBinding() {

    }
}