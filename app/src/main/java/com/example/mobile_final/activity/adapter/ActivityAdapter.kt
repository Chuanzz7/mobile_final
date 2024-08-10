package com.example.mobile_final.activity.adapter

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobile_final.databinding.ContainerActivityBinding
import com.example.mobile_final.databinding.PopupTaskBinding
import com.example.mobile_final.entity.Activity
import java.io.File

class ActivityAdapter(val context: Context, val parentView: View) :
    RecyclerView.Adapter<ActivityAdapter.ActivityItemModel>() {

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
        holder.itemBinding.txtActivityTitle.text = thisItem.title
        holder.itemBinding.txtActivityDescription.text = thisItem.description

        if (thisItem.imagePath != null) {
            holder.itemBinding.imageView.layoutParams.height = 400;
            holder.itemBinding.imageView.layoutParams.width = 400;
            Glide.with(holder.itemView).load(File(thisItem.imagePath!!))
                .into(holder.itemBinding.imageView)
        }

        holder.itemBinding.layout.setOnClickListener {
            val popUpBinding = PopupTaskBinding.inflate(LayoutInflater.from(context))
            val window =
                PopupWindow(
                    popUpBinding.root,
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
                    true
                )
            popUpBinding.txtTaskTitle.text = thisItem.title
            popUpBinding.txtTaskDescription.text = thisItem.description
            if (thisItem.imagePath != null) {
                Glide.with(popUpBinding.root).load(File(thisItem.imagePath!!))
                    .into(popUpBinding.imageView2)
            }
            window.showAtLocation(parentView, Gravity.CENTER, 0, 0)
        }
    }
}