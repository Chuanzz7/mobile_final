package com.example.mobile_final.activity.fragment

import android.content.Intent
import android.net.Uri
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
        }


        subjectViewModel.subject.observe(viewLifecycleOwner) {
            it?.let {
                binding.txtSubjectName.text = it.name
                binding.btnEnroll.isEnabled = !it.enrolled
                binding.btnEnroll.isClickable = !it.enrolled

                binding.txtNumberOfStudent.text =
                    resources.getString(
                        R.string.studentCount, it.studentAmount
                    )

                binding.txtCreditHour.text =
                    resources.getString(
                        R.string.creditHours, it.creditHours
                    )

                binding.txtYear.text =
                    resources.getString(
                        R.string.year, it.year
                    )

                binding.textSubjectDescription.text = it.description
            }
        }

        binding.btnConsultation.setOnClickListener {
            val email = "chngkahchuan@gmail.com"
            val subject = "Query on Subject"

            val addresses = email.split(",".toRegex()).toTypedArray()

            val mailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }

            startActivity(mailIntent)
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