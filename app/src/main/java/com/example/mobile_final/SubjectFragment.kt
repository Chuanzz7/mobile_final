package com.example.mobile_final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_final.database.AppDatabase
import com.example.mobile_final.databinding.FragmentSubjectBinding
import com.example.mobile_final.entity.SubjectDetails
import com.example.mobile_final.viewModel.SubjectViewModel
import com.example.mobile_final.viewModel.adapter.SubjectDetailsAdapter
import com.example.mobile_final.viewModel.factory.SubjectViewModelFactory


class SubjectFragment : Fragment() {
    private lateinit var binding: FragmentSubjectBinding
    private lateinit var subjectDetailsAdapter: SubjectDetailsAdapter
    private lateinit var subjectViewModel: SubjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubjectBinding.inflate(inflater, container, false)
        setupHomeRecyclerView()
        subjectViewModel.findById(2)
        return binding.root
    }


    private fun setupHomeRecyclerView() {
        val list: List<SubjectDetails> = listOf(SubjectDetails(1, "test", false))

        subjectDetailsAdapter = SubjectDetailsAdapter()
        binding.recycleViewSubjectDetails.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = subjectDetailsAdapter
        }
        activity?.let {
            subjectDetailsAdapter.differ.submitList(list)
//            subjectListViewModel.getAll()
//                .observe(viewLifecycleOwner) { x ->
//                    subjectListAdapter.differ.submitList(x)
//                }
        }
    }

    private fun setupViewModel() {
        val dao = AppDatabase.getInstance(requireActivity()).subjectDao()
        val factory = SubjectViewModelFactory(dao)
        subjectViewModel = ViewModelProvider(this, factory)[SubjectViewModel::class.java]
    }
}