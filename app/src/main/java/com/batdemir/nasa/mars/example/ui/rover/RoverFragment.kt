package com.batdemir.nasa.mars.example.ui.rover

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.batdemir.nasa.mars.example.R
import com.batdemir.nasa.mars.example.data.entities.db.PhotosModel
import com.batdemir.nasa.mars.example.databinding.FragmentRoverBinding
import com.batdemir.nasa.mars.example.ui.adapter.RoverAdapter
import com.batdemir.nasa.mars.example.ui.base.BaseFragment
import com.batdemir.nasa.mars.example.utils.Resource
import javax.inject.Inject

class RoverFragment(listener: ItemListener) :
        BaseFragment<FragmentRoverBinding>(R.layout.fragment_rover) {
    @Inject
    lateinit var viewModel: RoverViewModel
    private lateinit var filter: MenuItem
    private var dialog: AlertDialog? = null

    interface ItemListener {
        fun onClick(model: PhotosModel)
    }

    private val adapter: RoverAdapter by lazy {
        RoverAdapter(object : RoverAdapter.ItemListener {
            override fun onClick(model: PhotosModel) {
                listener.onClick(model)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        filter = menu.add("filter")
        filter.setIcon(R.drawable.ic_black_filter)
        filter.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        filter.setOnMenuItemClickListener {
            dialog!!.show()
            true
        }
    }

    override fun onAttach(context: Context) {
        (requireActivity() as MainActivity).mainComponent?.inject(this)
        super.onAttach(context)
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        viewModel.setIndex(requireArguments().getInt(EXTRA_INDEX))
        binding!!.adapter = adapter
    }

    override fun setupData() {
        viewModel.loadData().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    filter.isEnabled = true
                    binding!!.progressBar.visibility = View.GONE
                    if (it.data == null)
                        return@observe
                    viewModel.setData(it.data.photos)
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                Resource.Status.LOADING -> {
                    filter.isEnabled = false
                    binding!!.progressBar.visibility = View.VISIBLE
                }
            }
        })

        viewModel.data.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    override fun setupListener() {
        binding!!.rootFragmentRover.setOnRefreshListener {
            binding!!.rootFragmentRover.isRefreshing = false
            setupData()
        }
    }

    companion object {
        private const val EXTRA_INDEX = "titleIndex"
        fun newInstance(
                listener: ItemListener,
                value: Int
        ): RoverFragment {
            return RoverFragment(listener).apply {
                arguments = Bundle(1).apply {
                    putInt(EXTRA_INDEX, value)
                }
            }
        }
    }
}