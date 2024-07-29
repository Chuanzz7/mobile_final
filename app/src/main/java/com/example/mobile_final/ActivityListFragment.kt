package com.example.mobile_final

import ImageSaver
import android.app.ActionBar
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_final.database.AppDatabase
import com.example.mobile_final.databinding.FragmentActivityListBinding
import com.example.mobile_final.viewModel.ActivityViewModel
import com.example.mobile_final.viewModel.adapter.ActivityAdapter
import com.example.mobile_final.viewModel.factory.ActivityViewModelFactory


class ActivityListFragment : Fragment() {
    private lateinit var binding: FragmentActivityListBinding
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
        binding = FragmentActivityListBinding.inflate(inflater, container, false)
        setupHomeRecyclerView()
        binding.btnImage.setOnClickListener { pickPhoto() }
        binding.btnCreate.setOnClickListener { createPost() }
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
                .observe(viewLifecycleOwner) { x ->
                    activityAdapter.differ.submitList(x)
                }
        }
    }

    private fun pickPhoto() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            activityViewModel.imagePath.value = uri
            val imageView = ImageView(requireContext())
            imageView.setImageURI(uri)
            imageView.layout(0, 0, 0, 0)
            imageView.setLayoutParams(ActionBar.LayoutParams(400, 400))
            binding.createLayout.addView(imageView, 2)
        } else {
        }
    }

    private fun createPost() {
        val image: String?
        if (activityViewModel.imagePath.value != null) {
            image = ImageSaver.saveToInternalStorage(
                requireContext(),
                MediaStore.Images.Media.getBitmap(
                    requireContext().contentResolver,
                    activityViewModel.imagePath.value
                )
            )
        } else {
            image = null
        }

        activityViewModel.insertActivity(
            1,
            binding.txtTitle.text.toString(),
            binding.txtDescription.text.toString(),
            image
        );

        binding.txtTitle.text = null
        binding.txtDescription.text = null
        binding.createLayout.removeViewAt(2)
    }
}