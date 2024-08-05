package com.example.mobile_final.viewModel.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.databinding.ContainerTaskHeaderCompletedBinding
import com.example.mobile_final.dto.AssignmentTask
import com.example.mobile_final.entity.Task
import java.text.SimpleDateFormat
import java.util.Date

class TaskAdapterCompleted(val context: Context) :
    RecyclerView.Adapter<TaskAdapterCompleted.TaskItemModel>() {

    var onItemClick: ((Task) -> Unit)? = null

    class TaskItemModel(val itemBinding: ContainerTaskHeaderCompletedBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<AssignmentTask>() {
        override fun areContentsTheSame(
            oldItem: AssignmentTask,
            newItem: AssignmentTask
        ): Boolean {
            return oldItem.task.completed == newItem.task.completed
        }

        override fun areItemsTheSame(
            oldItem: AssignmentTask,
            newItem: AssignmentTask
        ): Boolean {
            return oldItem.task.id == newItem.task.id
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskItemModel {
        return TaskItemModel(
            ContainerTaskHeaderCompletedBinding.inflate(
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
        holder: TaskItemModel,
        position: Int
    ) {
        with(differ.currentList[position]) {
            holder.itemBinding.textContainerTaskContent.text = this.task.name
            holder.itemBinding.checkBoxTaskCompleted.isChecked = this.task.completed
            holder.itemBinding.txtAssignmentName.text = this.assignment.name
            holder.itemBinding.txtTaskDueDate.text =
                SimpleDateFormat("dd-MM-yyyy HH:mm a").format(this.task.dueDate)
            if (this.task.completed) {
                holder.itemBinding.textContainerTaskContent.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                holder.itemBinding.textContainerTaskContent.paintFlags =
                    Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

            holder.itemBinding.checkBoxTaskCompleted.setOnClickListener {
                this.task.completed = !this.task.completed
                if (this.task.completed) {
                    this.task.completed_time = Date()
                } else {
                    this.task.completed_time = null
                }
                onItemClick?.invoke(this.task)
                notifyDataSetChanged()
            }

            holder.itemBinding.expandedView.setOnClickListener {
                this.task.completed = !this.task.completed
                if (this.task.completed) {
                    this.task.completed_time = Date()
                } else {
                    this.task.completed_time = null
                }
                onItemClick?.invoke(this.task)
                notifyDataSetChanged()
            }
        }
    }
}