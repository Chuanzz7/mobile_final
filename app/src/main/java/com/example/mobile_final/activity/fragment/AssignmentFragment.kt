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
import com.example.mobile_final.viewModel.AssignmentViewModel
import com.example.mobile_final.viewModel.TaskViewModel
import com.example.mobile_final.activity.adapter.AssignmentAdapter
import com.example.mobile_final.viewModel.factory.AssignmentViewModelFactory
import com.example.mobile_final.viewModel.factory.TaskViewModelFactory


class AssignmentFragment : Fragment() {
    private lateinit var binding: FragmentAssignmentBinding
    private lateinit var assignmentViewModel: AssignmentViewModel
    private lateinit var assignmentListAdapter: AssignmentAdapter
    private lateinit var taskViewModel: TaskViewModel

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
        val dao = AppDatabase.getInstance(requireActivity()).assignmentDao()
        val factory = AssignmentViewModelFactory(dao);
        assignmentViewModel = ViewModelProvider(this, factory)[AssignmentViewModel::class.java]

        val taskDao = AppDatabase.getInstance(requireActivity()).taskDao()
        val taskFactory = TaskViewModelFactory(taskDao)
        taskViewModel = ViewModelProvider(this, taskFactory)[TaskViewModel::class.java]
    }

    private fun setupHomeRecyclerView() {
        assignmentListAdapter = AssignmentAdapter(requireContext())
        binding.recycleViewAssignment.apply {
            val temp = LinearLayoutManager(context)
            temp.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = temp
            adapter = assignmentListAdapter
        }
        assignmentListAdapter.onItemClick = {
            assignmentViewModel.submitAssignment(it)
            taskViewModel.updateAllAssignment(it)
        }
        activity?.let {
            assignmentViewModel.findAllAssignmentSubject().observe(viewLifecycleOwner) { x ->
                assignmentListAdapter.differ.submitList(x)
            }
        }
    }
}