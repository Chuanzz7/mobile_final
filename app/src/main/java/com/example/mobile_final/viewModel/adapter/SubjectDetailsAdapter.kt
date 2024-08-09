package com.example.mobile_final.viewModel.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.databinding.ContainerSubjectDetailsContentBinding

class SubjectDetailsAdapter() :
    RecyclerView.Adapter<SubjectDetailsAdapter.SubjectDetailsItemModel>() {

    class SubjectDetailsItemModel(val itemBinding: ContainerSubjectDetailsContentBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<HashMap<String, String>>() {
        override fun areContentsTheSame(
            oldItem: HashMap<String, String>,
            newItem: HashMap<String, String>
        ): Boolean {
            return oldItem.size == newItem.size
        }

        override fun areItemsTheSame(
            oldItem: HashMap<String, String>,
            newItem: HashMap<String, String>
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectDetailsItemModel {
        return SubjectDetailsItemModel(
            ContainerSubjectDetailsContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(
        holder: SubjectDetailsItemModel,
        position: Int
    ) {
        with(differ.currentList[position]) {
            holder.itemBinding.textContainerSubjectDetailsHeader.text = this["header"]
            holder.itemBinding.textContainerSubjectDetailsContent.text = this["content"]
            holder.itemBinding.expandedView.visibility =
                if (this["expand"] == "true") View.VISIBLE else View.GONE

            holder.itemBinding.layoutSubjectDetailsItem.setOnClickListener() {
                this["expand"] = if (this["expand"] == "true") "false" else "true"
                notifyDataSetChanged()
            }
        }
    }
}