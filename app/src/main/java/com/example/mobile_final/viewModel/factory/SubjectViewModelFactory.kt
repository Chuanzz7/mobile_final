package com.example.mobile_final.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_final.dao.SubjectDao
import com.example.mobile_final.viewModel.SubjectViewModel

class SubjectViewModelFactory(private val dao: SubjectDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubjectViewModel::class.java)) {
            return SubjectViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}