package com.example.mobile_final.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_final.dao.AssignmentDao
import com.example.mobile_final.dao.SubjectDao
import com.example.mobile_final.dao.TaskDao
import com.example.mobile_final.entity.Assignment

class HomeViewModel(
    private val taskDao: TaskDao,
    private val assignmentDao: AssignmentDao,
    private val subjectDao: SubjectDao
) :
    ViewModel() {

    fun taskCount(): LiveData<Int> {
        return taskDao.calculateActive();
    }

    fun assignmentCount(): LiveData<Int> {
        return assignmentDao.findAllEnrolledCount();
    }

    fun subjectCount(): LiveData<Int> {
        return subjectDao.calculateActive();
    }

    fun findRecentAssignment(): LiveData<Assignment>{
        return assignmentDao.findRecent();
    }
}