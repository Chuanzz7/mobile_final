package com.example.mobile_final.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_final.dao.AssignmentDao
import com.example.mobile_final.dao.SubjectDao
import com.example.mobile_final.dao.TaskDao
import com.example.mobile_final.viewModel.HomeViewModel

class HomeViewModelFactory(
    private val taskDao: TaskDao,
    private val assignmentDao: AssignmentDao,
    private val subjectDao: SubjectDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(taskDao, assignmentDao, subjectDao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}