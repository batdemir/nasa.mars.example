package com.batdemir.nasa.mars.example.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.batdemir.nasa.mars.example.R
import com.batdemir.nasa.mars.example.data.entities.ui.FilterModel
import com.batdemir.nasa.mars.example.databinding.ItemFilterBinding

class FilterAdapter(private val itemListener: ItemListener) :
        ListAdapter<FilterModel, FilterAdapter.FilterViewHolder>(Companion) {

    class FilterViewHolder(val binding: ItemFilterBinding) : RecyclerView.ViewHolder(binding.root)

    interface ItemListener {
        fun onClick(model: FilterModel)
    }

    companion object : DiffUtil.ItemCallback<FilterModel>() {
        override fun areItemsTheSame(
                oldItem: FilterModel,
                newItem: FilterModel
        ): Boolean =
                oldItem === newItem

        override fun areContentsTheSame(
                oldItem: FilterModel,
                newItem: FilterModel
        ): Boolean =
                oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
                DataBindingUtil.inflate<ItemFilterBinding>(
                        layoutInflater,
                        R.layout.item_filter,
                        parent,
                        false
                )
        return FilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val current = getItem(position)
        holder.binding.model = current
        holder.binding.root.setOnClickListener {
            itemListener.onClick(current)
        }
        holder.binding.executePendingBindings()
    }
}