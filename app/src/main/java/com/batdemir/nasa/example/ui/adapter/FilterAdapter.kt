package com.batdemir.nasa.example.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.batdemir.nasa.example.R
import com.batdemir.nasa.example.data.entities.ui.FilterModel
import com.batdemir.nasa.example.databinding.ItemFilterBinding

class FilterAdapter(private val listener: ItemListener) :
        ListAdapter<FilterModel, FilterAdapter.FilterViewHolder>(Companion) {

    interface ItemListener {
        fun onClick(model: FilterModel)
    }

    class FilterViewHolder(val binding: ItemFilterBinding) : RecyclerView.ViewHolder(binding.root)

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
        holder.binding.rootItemFilter.setOnClickListener {
            holder.binding.checkBox.isChecked = !holder.binding.checkBox.isChecked
        }
        holder.binding.checkBox.setOnCheckedChangeListener { _, _ ->
            listener.onClick(current)
        }
        holder.binding.executePendingBindings()
    }
}