package com.example.mobile_final.activity.fragment

import ImageSaver
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mobile_final.database.AppDatabase
import com.example.mobile_final.databinding.FragmentActivityListBinding
import com.example.mobile_final.viewModel.ActivityViewModel
import com.example.mobile_final.activity.adapter.ActivityAdapter
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
        setupHomeRecyclerView(binding.root)
        binding.btnImage.setOnClickListener { pickPhoto() }
        binding.btnCreate.setOnClickListener { createPost() }
        return binding.root
    }

    private fun setupViewModel() {
        val userAttemptDao = AppDatabase.getInstance(requireActivity()).activityDao()
        val factory = ActivityViewModelFactory(userAttemptDao);
        activityViewModel = ViewModelProvider(this, factory)[ActivityViewModel::class.java]
    }

    private fun setupHomeRecyclerView(view : View) {
        activityAdapter = ActivityAdapter(requireContext(),view)
        binding.recycleView.apply {
            layoutManager = StaggeredGridLayoutManager(2, 1)
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
            binding.uploadedImage.setImageURI(uri)
            binding.uploadedImage.layoutParams.width = 300
            binding.uploadedImage.layoutParams.height = 300
        } else {
        }
    }

    private fun createPost() {
        if (binding.txtTitle.text.isNullOrBlank()
            && binding.txtDescription.text.isNullOrBlank()
            && activityViewModel.imagePath.value == null) {
            Toast.makeText(
                requireContext(),
                "Please enter info!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }


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
            binding.txtTitle.text.toString(),
            binding.txtDescription.text.toString(),
            image
        );

        binding.txtTitle.text = null
        binding.txtDescription.text = null
        if (activityViewModel.imagePath.value != null) {
            activityViewModel.imagePath.value = null
            binding.uploadedImage.setImageDrawable(null)
            binding.uploadedImage.layoutParams.width = 0
            binding.uploadedImage.layoutParams.height = 0
        }

        Toast.makeText(
            requireContext(),
            "Notes Added!",
            Toast.LENGTH_SHORT
        ).show()
    }
}