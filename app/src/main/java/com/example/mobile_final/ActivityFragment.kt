package com.example.mobile_final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_final.database.AppDatabase
import com.example.mobile_final.databinding.FragmentActivityBinding
import com.example.mobile_final.viewModel.ActivityAdapter
import com.example.mobile_final.viewModel.ActivityViewModel
import com.example.mobile_final.viewModel.factory.ActivityViewModelFactory


class ActivityFragment : Fragment() {
    private lateinit var binding: FragmentActivityBinding
    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var activityAdapter: ActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActivityBinding.inflate(inflater, container, false)
        setupHomeRecyclerView()
        return binding.root
    }

    private fun setupViewModel() {
        val userAttemptDao = AppDatabase.getInstance(requireActivity()).activityDao()
        val factory = ActivityViewModelFactory(userAttemptDao);
        activityViewModel = ViewModelProvider(this, factory)[ActivityViewModel::class.java]
    }

    private fun setupHomeRecyclerView() {
        activityAdapter = ActivityAdapter()
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = activityAdapter
        }

        activity?.let {
            activityViewModel.getActivity()
                .observe(viewLifecycleOwner) { userAttempt ->
                    activityAdapter.differ.submitList(userAttempt)
                }
        }
    }
}