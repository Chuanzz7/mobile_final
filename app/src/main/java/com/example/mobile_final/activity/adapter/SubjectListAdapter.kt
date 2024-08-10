package com.example.mobile_final.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.R
import com.example.mobile_final.databinding.ContainerSubjectListBinding
import com.example.mobile_final.entity.Subject

class SubjectListAdapter(val context: Context) :
    RecyclerView.Adapter<SubjectListAdapter.SubjectItemModel>() {

    class SubjectItemModel(val itemBinding: ContainerSubjectListBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Subject>() {
        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectItemModel {
        return SubjectItemModel(
            ContainerSubjectListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SubjectItemModel, position: Int) {
        with(differ.currentList[position]) {
            if (this.enrolled) {
                holder.itemBinding.layoutSubjectListHighlight.background =
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.border_left_rounded_color
                    )
            }
            holder.itemBinding.txtSubjectCode.text = this.code
            holder.itemBinding.txtSubjectListName.text = this.name
            holder.itemBinding.txtLectureName.text = this.description
            val bundle = bundleOf("id" to this.id)
            holder.itemBinding.root.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_subjectListFragment_to_subjectFragment, bundle)
            }
        }
    }
}