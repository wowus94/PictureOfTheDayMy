package com.example.pictureofthedaymy.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureofthedaymy.databinding.FragmentRecyclerItemEarthBinding
import com.example.pictureofthedaymy.databinding.FragmentRecyclerItemHeaderBinding
import com.example.pictureofthedaymy.databinding.FragmentRecyclerItemMarsBinding

class RecyclerAdapter(val listData: List<Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_EARTH -> {
                val binding =
                    FragmentRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                return EarthViewHolder(binding)
            }
            TYPE_MARS -> {
                val binding =
                    FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                return MarsViewHolder(binding)
            }
            else -> {
                val binding =
                    FragmentRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                return HeaderViewHolder(binding)
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class HeaderViewHolder(val binding: FragmentRecyclerItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root)

    class EarthViewHolder(val binding: FragmentRecyclerItemEarthBinding) :
        RecyclerView.ViewHolder(binding.root)

    class MarsViewHolder(val binding: FragmentRecyclerItemMarsBinding) :
        RecyclerView.ViewHolder(binding.root)
}