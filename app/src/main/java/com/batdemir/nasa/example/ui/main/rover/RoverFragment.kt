package com.batdemir.nasa.example.ui.main.rover

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.filter
import com.batdemir.nasa.example.R
import com.batdemir.nasa.example.data.entities.db.PhotosModel
import com.batdemir.nasa.example.databinding.FragmentRoverBinding
import com.batdemir.nasa.example.ui.MainActivity
import com.batdemir.nasa.example.ui.adapter.RoverAdapter
import com.batdemir.nasa.example.ui.base.BaseFragment
import com.batdemir.nasa.example.utils.ZoomUtil
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoverFragment :
    BaseFragment<FragmentRoverBinding>(R.layout.fragment_rover) {

    @Inject
    lateinit var viewModel: RoverViewModel
    private lateinit var filter: MenuItem
    private lateinit var alertDialogBuilder: AlertDialog.Builder
    private val adapter: RoverAdapter by lazy {
        RoverAdapter(object : RoverAdapter.ItemListener {
            override fun onClick(model: PhotosModel) {
                ZoomUtil(requireContext()).zoomImageFromThumb(
                    binding!!.recyclerView,
                    model.imgSrc,
                    binding!!.popupView,
                    binding!!.rootFragmentRover
                )
                binding!!.popupView.populateDate(model)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        filter = menu.add("filter")
        filter.setIcon(R.drawable.ic_white_filter)
        filter.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        filter.setOnMenuItemClickListener {
            alertDialogBuilder.create().show()
            true
        }
    }

    override fun inject() {
        (requireActivity() as MainActivity).mainComponent?.inject(this)
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        viewModel.setIndex(requireArguments().getInt(EXTRA_INDEX))
        viewModel.setDynamic(requireArguments().getBoolean(EXTRA_IS_DYNAMIC))
        binding!!.adapter = adapter
        setupAlertDialog()
    }

    override fun setupData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadData().collectLatest {
                var data = it
                if (!viewModel._isDynamic) {
                    val filterData = viewModel.filterData.value!!
                        .filter { x -> x.isSelected }
                    data = if (filterData.count() > 0) {
                        data.filter { item ->
                            filterData
                                .map { x -> x.title }
                                .contains(item.camera.name)
                        }
                    } else {
                        it
                    }
                }
                adapter.submitData(data)
                if (adapter.itemCount == 0) {
                    binding!!.recyclerView.visibility = View.GONE
                    binding!!.viewEmpty.visibility = View.VISIBLE
                } else {
                    binding!!.recyclerView.visibility = View.VISIBLE
                    binding!!.viewEmpty.visibility = View.GONE
                }
            }
        }
        viewModel.filterData.observe(viewLifecycleOwner, {
            alertDialogBuilder.setMultiChoiceItems(
                it.map { x -> x.title }.toTypedArray(),
                it.map { x -> x.isSelected }.toBooleanArray(),
                DialogInterface.OnMultiChoiceClickListener { _, which, isChecked ->
                    it.forEachIndexed { index, item ->
                        if (index == which)
                            item.isSelected = isChecked
                    }
                }
            )
        })
    }

    override fun setupListener() {
        adapter.addLoadStateListener { loadState ->
            when {
                loadState.append is LoadState.Loading ||
                        loadState.refresh is LoadState.Loading ||
                        loadState.prepend is LoadState.Loading
                -> binding!!.progressBar.visibility =
                    View.VISIBLE
                else -> {
                    binding!!.progressBar.visibility = View.GONE
                    val error = when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    error?.let {
                        Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setupAlertDialog() {
        alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setPositiveButton(
            getString(R.string.ok)
        ) { _, _ -> setupData() }
        alertDialogBuilder.setNegativeButton(
            getString(R.string.cancel)
        ) { _, _ ->
            viewModel.filterData.value!!.forEach { item -> item.isSelected = false }
            setupData()
        }
    }

    companion object {
        private const val EXTRA_INDEX = "titleIndex"
        private const val EXTRA_IS_DYNAMIC = "isDynamic"
        fun newInstance(
            value: Int,
            isDynamic: Boolean
        ): RoverFragment {
            return RoverFragment().apply {
                arguments = Bundle(2).apply {
                    putInt(EXTRA_INDEX, value)
                    putBoolean(EXTRA_IS_DYNAMIC, isDynamic)
                }
            }
        }
    }
}