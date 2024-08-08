package com.example.mobile_final.viewModel.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.R
import com.example.mobile_final.databinding.ContainerAssignmentListBinding
import com.example.mobile_final.dto.AssignmentSubject
import java.text.SimpleDateFormat

class AssignmentAdapter(val context: Context) :
    RecyclerView.Adapter<AssignmentAdapter.AssignmentItemModel>() {
    var onItemClick: ((Int) -> Unit)? = null

    class AssignmentItemModel(val itemBinding: ContainerAssignmentListBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<AssignmentSubject>() {
        override fun areContentsTheSame(
            oldItem: AssignmentSubject,
            newItem: AssignmentSubject
        ): Boolean {
            return oldItem.assignment.submitted == newItem.assignment.submitted
        }

        override fun areItemsTheSame(
            oldItem: AssignmentSubject,
            newItem: AssignmentSubject
        ): Boolean {
            return oldItem.assignment.id == newItem.assignment.id
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

            holder.itemBinding.txtAssignmentName.text = this.assignment.name
            holder.itemBinding.txtSubjectName.text = this.subject.name
            holder.itemBinding.txtAssignmentWeight.text = "( " + this.assignment.weightage + " )"
            holder.itemBinding.txtAssignmentDescription.text = this.assignment.description
            holder.itemBinding.txtDeliverables.text = this.assignment.deliverables
            holder.itemBinding.txtAssignmentDue.paint.isUnderlineText = true
            holder.itemBinding.txtAssignmentDue.text = SimpleDateFormat("dd-MM-yyyy").format(this.assignment.dueDate)
            holder.itemBinding.btnTaskNavigate.setOnClickListener {
                it.findNavController().navigate(R.id.action_assignmentFragment_to_taskFragment)
            }
            if (this.assignment.submitted) {
                holder.itemBinding.txtAssignmentName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                holder.itemBinding.btnSubmitAssignment.isEnabled = false
                holder.itemBinding.btnSubmitAssignment.isClickable = false
            } else {
                holder.itemBinding.btnSubmitAssignment.setOnClickListener {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                    builder
                        .setMessage("All related task will be completed")
                        .setTitle("Submit Assignment?")
                        .setPositiveButton("Yes") { dialog, which ->
                            onItemClick?.invoke(this.assignment.id)
                        }
                        .setNegativeButton("No") { dialog, which ->
                            // Do something else.
                        }

                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            }
        }
    }
}