package com.batdemir.nasa.mars.example.ui.rover.filter

import android.content.Context
import android.os.Bundle
import com.batdemir.nasa.mars.example.R
import com.batdemir.nasa.mars.example.data.entities.ui.FilterModel
import com.batdemir.nasa.mars.example.databinding.FragmentFilterBinding
import com.batdemir.nasa.mars.example.ui.adapter.FilterAdapter
import com.batdemir.nasa.mars.example.ui.base.BaseFragment
import javax.inject.Inject

class FilterFragment : BaseFragment<FragmentFilterBinding>(R.layout.fragment_filter) {
    @Inject
    lateinit var viewModel: FilterViewModel

    private val adapter: FilterAdapter = FilterAdapter(itemListener = object : FilterAdapter.ItemListener {
        override fun onClick(model: FilterModel) {
            //TODO("Not yet implemented")
        }
    })

    override fun onAttach(context: Context) {
        (requireActivity() as MainActivity).mainComponent?.inject(this)
        super.onAttach(context)
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        //TODO("Not yet implemented")
    }

    override fun setupData() {
        //TODO("Not yet implemented")
    }

    override fun setupListener() {
        TODO("Not yet implemented")
    }
}