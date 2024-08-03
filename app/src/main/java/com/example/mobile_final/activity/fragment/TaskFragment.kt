package com.example.mobile_final.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_final.R
import com.example.mobile_final.database.AppDatabase
import com.example.mobile_final.databinding.FragmentTaskBinding
import com.example.mobile_final.viewModel.TaskViewModel
import com.example.mobile_final.viewModel.adapter.TaskAdapter
import com.example.mobile_final.viewModel.factory.TaskViewModelFactory


class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        setupHomeRecyclerView()

        this.binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_taskFragment_to_createTaskFragment)
        }
        return binding.root
    }


    private fun setupHomeRecyclerView() {
        taskAdapter = TaskAdapter(requireContext())
        binding.recycleViewTask.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }
        //TODO fix this
        taskAdapter.onItemClick = {
            taskViewModel.update(it)
            binding.recycleViewTask.swapAdapter(taskAdapter, true)
        }
        activity?.let {
            taskViewModel.findAll2()
                .observe(viewLifecycleOwner) { taskAdapter.differ.submitList(it) }
        }
    }

    private fun setupViewModel() {
        val dao = AppDatabase.getInstance(requireActivity()).taskDao()
        val factory = TaskViewModelFactory(dao)
        taskViewModel = ViewModelProvider(this, factory)[TaskViewModel::class.java]
    }
}