package com.example.mobile_final.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_final.dao.AssignmentDao
import com.example.mobile_final.entity.Assignment

class AssignmentViewModel(private val assignmentDao: AssignmentDao) : ViewModel() {

    fun findAll(): LiveData<List<Assignment>> {
        return assignmentDao.findAll()
    }
}