package com.example.mobile_final.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_final.dao.TaskDao
import com.example.mobile_final.viewModel.TaskViewModel

class TaskViewModelFactory(private val dao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}