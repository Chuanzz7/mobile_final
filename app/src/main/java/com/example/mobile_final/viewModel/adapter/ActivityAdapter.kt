package com.example.mobile_final.viewModel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobile_final.databinding.ContainerActivityBinding
import com.example.mobile_final.entity.Activity
import java.io.File

class ActivityAdapter : RecyclerView.Adapter<ActivityAdapter.ActivityItemModel>() {

    class ActivityItemModel(val itemBinding: ContainerActivityBinding) :
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
            ContainerActivityBinding.inflate(
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
        val thisItem = differ.currentList[position]
        holder.itemBinding.txtActivityUsername.text = thisItem.description
        if (thisItem.imagePath != null) {
            Glide.with(holder.itemView).load(File(thisItem.imagePath!!))
                .into(holder.itemBinding.imageView)
        }
    }
}