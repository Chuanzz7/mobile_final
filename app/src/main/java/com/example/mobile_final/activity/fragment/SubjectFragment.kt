package com.example.mobile_final.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_final.R
import com.example.mobile_final.database.AppDatabase
import com.example.mobile_final.databinding.FragmentSubjectBinding
import com.example.mobile_final.viewModel.SubjectViewModel
import com.example.mobile_final.viewModel.adapter.SubjectDetailsAdapter
import com.example.mobile_final.viewModel.factory.SubjectViewModelFactory

class SubjectFragment : Fragment() {
    private lateinit var binding: FragmentSubjectBinding
    private lateinit var subjectDetailsAdapter: SubjectDetailsAdapter
    private lateinit var subjectViewModel: SubjectViewModel
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        id = arguments?.getInt("id")
        id?.let { subjectViewModel.findById(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubjectBinding.inflate(inflater, container, false)

        binding.btnEnroll.setOnClickListener {
            subjectViewModel.enroll()
            Toast.makeText(requireContext(), "Subject Enrolled !", Toast.LENGTH_SHORT).show()
        }

        subjectViewModel.subject.observe(viewLifecycleOwner) {
            it?.let {
                binding.txtSubjectName.text = it.name
                binding.btnEnroll.isEnabled = !it.enrolled
                binding.btnEnroll.isClickable = !it.enrolled
            }
        }

        binding.btnBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_subjectFragment_to_subjectListFragment)
        }

        setupHomeRecyclerView()
        return binding.root
    }


    private fun setupHomeRecyclerView() {
        subjectDetailsAdapter = SubjectDetailsAdapter()
        binding.recycleViewSubjectDetails.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = subjectDetailsAdapter
        }
        activity?.let {
            subjectViewModel.liveDetails
                .observe(viewLifecycleOwner) { subjectDetailsAdapter.differ.submitList(it) }
        }
    }

    private fun setupViewModel() {
        val dao = AppDatabase.getInstance(requireActivity()).subjectDao()
        val factory = SubjectViewModelFactory(dao)
        subjectViewModel = ViewModelProvider(this, factory)[SubjectViewModel::class.java]
    }
}