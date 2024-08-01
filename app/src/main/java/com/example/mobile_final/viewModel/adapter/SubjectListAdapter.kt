package com.example.mobile_final.viewModel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_final.R
import com.example.mobile_final.databinding.ContainerSubjectListBinding
import com.example.mobile_final.entity.Subject

class SubjectListAdapter : RecyclerView.Adapter<SubjectListAdapter.SubjectItemModel>() {

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
        val thisItem = differ.currentList[position]
        holder.itemBinding.txtSubjectListName.text = thisItem.name
        holder.itemBinding.txtLectureName.text = thisItem.description
        val bundle = bundleOf("id" to thisItem.id)
        holder.itemBinding.root.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_subjectListFragment_to_subjectFragment, bundle)
        }
    }
}