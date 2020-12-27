package com.batdemir.nasa.example.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.batdemir.nasa.example.R
import com.batdemir.nasa.example.data.entities.db.PhotosModel
import com.batdemir.nasa.example.databinding.ItemRoverBinding

class RoverAdapter(private val itemListener: ItemListener) :
    PagingDataAdapter<PhotosModel, RoverAdapter.RoverViewHolder>(Companion) {

    class RoverViewHolder(val binding: ItemRoverBinding) : RecyclerView.ViewHolder(binding.root)

    interface ItemListener {
        fun onClick(model: PhotosModel)
    }

    companion object : DiffUtil.ItemCallback<PhotosModel>() {
        override fun areItemsTheSame(
            oldItem: PhotosModel,
            newItem: PhotosModel
        ): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: PhotosModel,
            newItem: PhotosModel
        ): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemRoverBinding>(
                layoutInflater,
                R.layout.item_rover,
                parent,
                false
            )
        return RoverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoverViewHolder, position: Int) {
        val current = getItem(position)
        holder.binding.model = current
        holder.binding.root.setOnClickListener {
            itemListener.onClick(current!!)
        }
        holder.binding.executePendingBindings()
    }
}