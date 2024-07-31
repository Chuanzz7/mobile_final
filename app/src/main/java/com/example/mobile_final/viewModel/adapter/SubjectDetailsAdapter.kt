package com.example.mobile_final.viewModel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.databinding.ContainerSubjectDetailsContentBinding
import com.example.mobile_final.entity.SubjectDetails

class SubjectDetailsAdapter() :
    RecyclerView.Adapter<SubjectDetailsAdapter.SubjectDetailsItemModel>() {

    class SubjectDetailsItemModel(val itemBinding: ContainerSubjectDetailsContentBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<SubjectDetails>() {
        override fun areContentsTheSame(
            oldItem: SubjectDetails,
            newItem: SubjectDetails
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(
            oldItem: SubjectDetails,
            newItem: SubjectDetails
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
            holder.itemBinding.textContainerSubjectDetailsHeader.text = "Header"
            holder.itemBinding.textContainerSubjectDetailsContent.text = "Content"
            holder.itemBinding.expandedView.visibility =
                if (this.expand) View.VISIBLE else View.GONE

            holder.itemBinding.materialCardView.setOnClickListener() {
                this.expand = !this.expand
                notifyDataSetChanged()
            }
        }
    }
}