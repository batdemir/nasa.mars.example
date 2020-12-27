package com.batdemir.nasa.mars.example.ui.main

import android.content.Context
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.batdemir.nasa.mars.example.R
import com.batdemir.nasa.mars.example.data.entities.db.PhotosModel
import com.batdemir.nasa.mars.example.databinding.FragmentMainBinding
import com.batdemir.nasa.mars.example.ui.adapter.RoverCollectionAdapter
import com.batdemir.nasa.mars.example.ui.base.BaseFragment
import com.batdemir.nasa.mars.example.ui.rover.RoverFragment
import com.batdemir.nasa.mars.example.ui.rover.RoverType
import com.batdemir.nasa.mars.example.utils.ZoomOutPageTransformer
import com.batdemir.nasa.mars.example.utils.ZoomUtil
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var viewPager: ViewPager2

    override fun onAttach(context: Context) {
        (requireActivity() as MainActivity).mainComponent?.inject(this)
        super.onAttach(context)
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        viewPager = binding!!.viewPager
        viewPager.isUserInputEnabled = false
        viewPager.setPageTransformer(ZoomOutPageTransformer())
        setupAdapter()
    }

    override fun setupData() {
    }

    override fun setupListener() {
    }

    private fun setupAdapter() {
        val adapter = RoverCollectionAdapter(
                this,
                object : RoverFragment.ItemListener {
                    override fun onClick(model: PhotosModel) {
                        ZoomUtil(requireContext()).zoomImageFromThumb(
                                viewPager,
                                model.imgSrc,
                                binding!!.imageViewExpanded,
                                binding!!.rootFragmentMain
                        )
                    }
                })

        viewPager.adapter = adapter

        TabLayoutMediator(binding!!.tabLayout, viewPager) { tab, position ->
            when (position) {
                RoverType.CURIOSITY_INDEX.value -> tab.text = RoverType.CURIOSITY_INDEX.strValue
                RoverType.OPPORTUNITY_INDEX.value -> tab.text =
                        RoverType.OPPORTUNITY_INDEX.strValue
                RoverType.SPIRIT_INDEX.value -> tab.text = RoverType.SPIRIT_INDEX.strValue
            }
        }.attach()
    }
}