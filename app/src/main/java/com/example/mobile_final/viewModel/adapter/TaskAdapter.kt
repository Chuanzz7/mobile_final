package com.example.mobile_final.viewModel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.databinding.ContainerTaskHeaderBinding
import com.example.mobile_final.dto.AssignmentTask
import com.example.mobile_final.entity.Task

class TaskAdapter(val context: Context) :
    RecyclerView.Adapter<TaskAdapter.TaskItemModel>() {

    var onItemClick: ((Task) -> Unit)? = null
    private var assignmentHeaderId: Int? = null

    class TaskItemModel(val itemBinding: ContainerTaskHeaderBinding) :
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
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskItemModel {
        return TaskItemModel(
            ContainerTaskHeaderBinding.inflate(
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
            val layout = holder.itemBinding.layoutTaskHeader

            if (assignmentHeaderId != this.assignment.id) {
                assignmentHeaderId = this.assignment.id
                val textView = TextView(context)
                textView.setText(this.assignment.name)
                layout.addView(textView, 0)
            }

            holder.itemBinding.textContainerTaskContent.text = this.task.name
            holder.itemBinding.checkBoxTaskCompleted.isChecked = this.task.completed
            holder.itemBinding.expandedView.setOnClickListener {
                this.task.completed = !this.task.completed
                onItemClick?.invoke(this.task)
            }
        }
    }
}