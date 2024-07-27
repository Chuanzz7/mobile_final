package com.example.mobile_final.viewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.databinding.ActivityItemBinding
import com.example.mobile_final.entity.Activity

class ActivityAdapter : RecyclerView.Adapter<ActivityAdapter.ActivityItemModel>() {

    class ActivityItemModel(val itemBinding: ActivityItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Activity>() {
        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityItemModel {
        return ActivityItemModel(
            ActivityItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ActivityItemModel, position: Int) {
        val currentHistory = differ.currentList[position]
        holder.itemBinding.txtActivityUsername.text = "Test"
    }
}