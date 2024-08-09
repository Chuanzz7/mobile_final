package com.example.mobile_final.viewModel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.databinding.ViewholderMyTasksBinding
import com.example.mobile_final.entity.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

class HomeTaskAdapter() :
    RecyclerView.Adapter<HomeTaskAdapter.HomeTaskModel>() {

    class HomeTaskModel(val itemBinding: ViewholderMyTasksBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Task>() {
        override fun areContentsTheSame(
            oldItem: Task,
            newItem: Task
        ): Boolean {
            return oldItem.completed == newItem.completed
        }

        override fun areItemsTheSame(
            oldItem: Task,
            newItem: Task
        ): Boolean {
            return oldItem.id == newItem.id
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

            val diff = this.dueDate.time - Date().time
            val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)

//            holder.itemBinding.dueDays.text = days.toString() + "Days"
//            holder.itemBinding.titleTxt.text = this.name
//            holder.itemBinding.dueDateTxt.text =
//                SimpleDateFormat("dd-MM-yyyy HH:mm a").format(this.dueDate)
//            if (30 - days < 0) {
//                holder.itemBinding.progressBar.progress = 0
//            } else {
//                holder.itemBinding.progressBar.progress = (30 - days / 30 * 100).toInt()
//            }
        }
    }
}