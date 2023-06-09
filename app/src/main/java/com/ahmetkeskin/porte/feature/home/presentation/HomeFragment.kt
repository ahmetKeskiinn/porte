package com.ahmetkeskin.porte.feature.home.presentation

import android.text.Editable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ahmetkeskin.porte.base.BaseFragment
import com.ahmetkeskin.porte.R
import com.ahmetkeskin.porte.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    layoutId = R.layout.fragment_home
) {
    override fun onInitDataBinding() {
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding.vieModel = viewModel
        initClickListener()
    }

    private fun initClickListener() {
        binding.continueButton.setOnClickListener { continueWithToken() }
        getToken()
    }

    private fun getToken() {
        val token = viewModel.getToken()
        if (!token.isNullOrEmpty()) binding.apiKey.text =
            Editable.Factory.getInstance().newEditable(viewModel.getToken())
    }

    private fun setToken() {
        viewModel.setToken(binding.apiKey.text.toString())
    }

    private fun continueWithToken() {
        if (checkToken()) {
            setToken()
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(getUIToken())
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun getUIToken() = binding.apiKey.text.toString()

    private fun checkToken(): Boolean {
        return !binding.apiKey.text.toString().isNullOrEmpty()
    }

}