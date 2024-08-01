package com.example.mobile_final.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_final.dao.AssignmentDao

class AssignmentViewModelFactory(private val dao: AssignmentDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssignmentViewModelFactory::class.java)) {
            return AssignmentViewModelFactory(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}