package com.example.mobile_final.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_final.R
import com.example.mobile_final.database.AppDatabase
import com.example.mobile_final.databinding.FragmentHomeBinding
import com.example.mobile_final.viewModel.HomeViewModel
import com.example.mobile_final.viewModel.adapter.HomeAssignmentAdapter
import com.example.mobile_final.viewModel.adapter.HomeTaskAdapter
import com.example.mobile_final.viewModel.factory.HomeViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var assignmentAdapter: HomeAssignmentAdapter
    private lateinit var taskAdapter: HomeTaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupAssignmentAdapter()
        setupTaskAdapter()
        homeViewModel.findMyProgress()

        homeViewModel.subjectCount().observe(viewLifecycleOwner) {
            it?.let {
                binding.txtSubjectRemain.text = it.toString()
            }
        }
        homeViewModel.assignmentCount().observe(viewLifecycleOwner) {
            it?.let {
                binding.txtAssignmentRemain.text = it.toString()
            }
        }
        homeViewModel.taskCount().observe(viewLifecycleOwner) {
            it?.let {
                binding.txtTaskRemain.text = it.toString()
            }
        }

        binding.recentViewAllTxt.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_assignmentFragment)
        }

        binding.myTaskViewAllTxt.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_taskFragment)
        }


        return binding.root
    }

    private fun setupViewModel() {
        val subjectDao = AppDatabase.getInstance(requireActivity()).subjectDao()
        val assignmentDao = AppDatabase.getInstance(requireActivity()).assignmentDao()
        val taskDao = AppDatabase.getInstance(requireActivity()).taskDao()
        val factory = HomeViewModelFactory(taskDao, assignmentDao, subjectDao)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private fun setupAssignmentAdapter() {
        assignmentAdapter = HomeAssignmentAdapter()
        binding.recycleViewHomeAssignment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = assignmentAdapter
        }
        activity?.let {
            homeViewModel.findRecentAssignment()
                .observe(viewLifecycleOwner) {
                    assignmentAdapter.differ.submitList(it)
                }
        }
    }

    private fun setupTaskAdapter() {
        taskAdapter = HomeTaskAdapter(requireContext())
        binding.myTaskView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = taskAdapter
        }
        activity?.let {
            homeViewModel.taskAssignment
                .observe(viewLifecycleOwner) {
                    taskAdapter.differ.submitList(it)
                }
        }
    }
}