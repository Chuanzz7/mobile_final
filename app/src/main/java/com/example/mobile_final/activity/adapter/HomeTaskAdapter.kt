package com.example.mobile_final.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.R
import com.example.mobile_final.databinding.ViewholderMyTasksBinding
import com.example.mobile_final.dto.HomeTask

class HomeTaskAdapter(val context: Context) :
    RecyclerView.Adapter<HomeTaskAdapter.HomeTaskModel>() {

    class HomeTaskModel(val itemBinding: ViewholderMyTasksBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<HomeTask>() {
        override fun areContentsTheSame(
            oldItem: HomeTask,
            newItem: HomeTask
        ): Boolean {
            return false //oldItem.taskTotalCount == newItem.taskTotalCount
        }

        override fun areItemsTheSame(
            oldItem: HomeTask,
            newItem: HomeTask
        ): Boolean {
            return oldItem.assignmentName == newItem.assignmentName
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeTaskModel {
        return HomeTaskModel(
            ViewholderMyTasksBinding.inflate(
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
        holder: HomeTaskModel,
        position: Int
    ) {
        with(differ.currentList[position]) {
            holder.itemBinding.assignmentName.text = this.assignmentName
            holder.itemBinding.taskProgressBar.progress =
                ((this.taskCompletedCount / this.taskTotalCount) * 100).toInt()

            holder.itemBinding.txtMyProgressCount.text = context.getString(
                R.string.my_progress_count,
                (this.taskCompletedCount).toInt(),
                (this.taskTotalCount).toInt()
            )
        }
    }
}