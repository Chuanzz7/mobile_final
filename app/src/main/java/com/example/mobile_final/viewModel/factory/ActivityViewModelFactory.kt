package com.example.mobile_final.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_final.dao.ActivityDao
import com.example.mobile_final.viewModel.ActivityViewModel

class ActivityViewModelFactory(
    private val dao: ActivityDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            return ActivityViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}