package com.batdemir.nasa.example.ui.main

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.batdemir.nasa.example.R
import com.batdemir.nasa.example.databinding.FragmentMainBinding
import com.batdemir.nasa.example.ui.MainActivity
import com.batdemir.nasa.example.ui.adapter.RoverCollectionAdapter
import com.batdemir.nasa.example.ui.base.BaseFragment
import com.batdemir.nasa.example.ui.main.rover.RoverType
import com.batdemir.nasa.example.utils.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    @Inject
    lateinit var viewModel: MainViewModel
    private val args: MainFragmentArgs by navArgs()

    override fun inject() {
        (requireActivity() as MainActivity).mainComponent?.inject(this)
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        binding!!.viewPager.isUserInputEnabled = false
        binding!!.viewPager.setPageTransformer(ZoomOutPageTransformer())
        val adapter = RoverCollectionAdapter(this@MainFragment)
        adapter.setDynamic(args.isDynamic)
        binding!!.viewPager.adapter = adapter
        TabLayoutMediator(binding!!.tabLayout, binding!!.viewPager) { tab, position ->
            when (position) {
                RoverType.CURIOSITY_INDEX.value -> tab.text =
                        RoverType.CURIOSITY_INDEX.strValue
                RoverType.OPPORTUNITY_INDEX.value -> tab.text =
                        RoverType.OPPORTUNITY_INDEX.strValue
                RoverType.SPIRIT_INDEX.value -> tab.text = RoverType.SPIRIT_INDEX.strValue
            }
        }.attach()
    }

    override fun setupData() {
        //TODO("Not yet implemented")
    }

    override fun setupListener() {
        //TODO("Not yet implemented")
    }
}