package com.example.mobile_final.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_final.database.AppDatabase
import com.example.mobile_final.databinding.FragmentHomeBinding
import com.example.mobile_final.viewModel.HomeViewModel
import com.example.mobile_final.viewModel.factory.HomeViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
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


        return binding.root
    }

    private fun setupViewModel() {
        val subjectDao = AppDatabase.getInstance(requireActivity()).subjectDao()
        val assignmentDao = AppDatabase.getInstance(requireActivity()).assignmentDao()
        val taskDao = AppDatabase.getInstance(requireActivity()).taskDao()

        val factory = HomeViewModelFactory(taskDao, assignmentDao, subjectDao)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }
}