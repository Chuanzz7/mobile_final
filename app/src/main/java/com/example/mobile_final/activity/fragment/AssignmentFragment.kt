package com.example.mobile_final.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_final.database.AppDatabase
import com.example.mobile_final.databinding.FragmentAssignmentBinding
import com.example.mobile_final.databinding.FragmentSubjectListBinding
import com.example.mobile_final.viewModel.SubjectListViewModel
import com.example.mobile_final.viewModel.adapter.SubjectListAdapter
import com.example.mobile_final.viewModel.factory.SubjectListViewModelFactory


class AssignmentFragment : Fragment() {
    private lateinit var binding: FragmentAssignmentBinding
    private lateinit var subjectListViewModel: SubjectListViewModel
    private lateinit var subjectListAdapter: SubjectListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAssignmentBinding.inflate(inflater, container, false)
        setupHomeRecyclerView()
        return binding.root
    }

    private fun setupViewModel() {
        val dao = AppDatabase.getInstance(requireActivity()).subjectDao()
        val factory = SubjectListViewModelFactory(dao);
        subjectListViewModel = ViewModelProvider(this, factory)[SubjectListViewModel::class.java]
    }

    private fun setupHomeRecyclerView() {
        subjectListAdapter = SubjectListAdapter()
        binding.recycleViewAssignment.apply {
            val temp = LinearLayoutManager(context)
            temp.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = temp
            adapter = subjectListAdapter
        }

        activity?.let {
            subjectListViewModel.getAll()
                .observe(viewLifecycleOwner) { x ->
                    subjectListAdapter.differ.submitList(x)
                }
        }
    }
}