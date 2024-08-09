package com.example.mobile_final.viewModel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.databinding.ViewholderRecentAssignmentsBinding
import com.example.mobile_final.entity.Assignment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

class HomeAssignmentAdapter() :
    RecyclerView.Adapter<HomeAssignmentAdapter.AssignmentItemModel>() {

    class AssignmentItemModel(val itemBinding: ViewholderRecentAssignmentsBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Assignment>() {
        override fun areContentsTheSame(
            oldItem: Assignment,
            newItem: Assignment
        ): Boolean {
            return oldItem.submitted == newItem.submitted
        }

        override fun areItemsTheSame(
            oldItem: Assignment,
            newItem: Assignment
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssignmentItemModel {
        return AssignmentItemModel(
            ViewholderRecentAssignmentsBinding.inflate(
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

            val diff = this.dueDate.time - Date().time
            val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)

            holder.itemBinding.dueDays.text = days.toString() + "Days"
            holder.itemBinding.titleTxt.text = this.name
            holder.itemBinding.dueDateTxt.text =
                SimpleDateFormat("dd-MM-yyyy HH:mm a").format(this.dueDate)
            if (30 - days < 0) {
                holder.itemBinding.progressBar.progress = 0
            } else {
                holder.itemBinding.progressBar.progress = (30 - days / 30 * 100).toInt()
            }
        }
    }
}