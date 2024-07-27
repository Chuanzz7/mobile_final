package com.example.mobile_final.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_final.dao.SubjectDao
import com.example.mobile_final.viewModel.SubjectListViewModel

class SubjectListViewModelFactory(private val dao: SubjectDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubjectListViewModel::class.java)) {
            return SubjectListViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}