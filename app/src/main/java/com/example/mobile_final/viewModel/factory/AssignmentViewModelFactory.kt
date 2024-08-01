package com.example.mobile_final.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_final.dao.AssignmentDao
import com.example.mobile_final.viewModel.AssignmentViewModel

class AssignmentViewModelFactory(private val dao: AssignmentDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssignmentViewModel::class.java)) {
            return AssignmentViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}