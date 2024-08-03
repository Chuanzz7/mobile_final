package com.example.mobile_final.viewModel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.R
import com.example.mobile_final.databinding.ContainerAssignmentListBinding
import com.example.mobile_final.entity.Assignment

class AssignmentAdapter : RecyclerView.Adapter<AssignmentAdapter.AssignmentItemModel>() {

    class AssignmentItemModel(val itemBinding: ContainerAssignmentListBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Assignment>() {
        override fun areContentsTheSame(
            oldItem: Assignment,
            newItem: Assignment
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(
            oldItem: Assignment,
            newItem: Assignment
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssignmentItemModel {
        return AssignmentItemModel(
            ContainerAssignmentListBinding.inflate(
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
        holder: AssignmentItemModel,
        position: Int
    ) {
        with(differ.currentList[position]) {
            holder.itemBinding.button.setOnClickListener {
                it.findNavController().navigate(R.id.action_assignmentFragment_to_taskFragment)
            }
//            holder.itemBinding.textContainerSubjectDetailsHeader.text = this["header"]
//            holder.itemBinding.textContainerSubjectDetailsContent.text = this["content"]
//            holder.itemBinding.expandedView.visibility =
//                if (this["expand"] == "true") View.VISIBLE else View.GONE
//
//            holder.itemBinding.layoutSubjectDetailsItem.setOnClickListener() {
//                this["expand"] = if (this["expand"] == "true") "false" else "true"
//                notifyDataSetChanged()
//            }
        }
    }
}