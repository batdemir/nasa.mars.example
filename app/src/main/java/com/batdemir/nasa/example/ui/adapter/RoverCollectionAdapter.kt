package com.batdemir.nasa.example.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.batdemir.nasa.example.ui.main.rover.RoverFragment

class RoverCollectionAdapter(
        private val fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private var isDynamic: Boolean = true

    override fun createFragment(
            position: Int
    ): Fragment =
            RoverFragment.newInstance(position, isDynamic)

    override fun getItemCount() = 3

    override fun onBindViewHolder(
            holder: FragmentViewHolder,
            position: Int,
            payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val tag = "f" + holder.itemId
            val fragment = fragment.requireActivity().supportFragmentManager.findFragmentByTag(tag)
            // safe check ,but fragment should not be null
            if (fragment != null) {
                return
            } else {
                super.onBindViewHolder(holder, position, payloads)
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    fun setDynamic(value: Boolean) {
        isDynamic = value
    }
}