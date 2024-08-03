package com.example.mobile_final.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_final.dao.AssignmentDao
import com.example.mobile_final.entity.Assignment
import kotlinx.coroutines.launch

class AssignmentViewModel(private val assignmentDao: AssignmentDao) : ViewModel() {

    var assignmentList = MutableLiveData<List<Assignment>>();

    fun findAll(): LiveData<List<Assignment>> {
        return assignmentDao.findAll()
    }

     fun findAllMutable() = viewModelScope.launch {
        assignmentDao.findAllTemp().collect {
            assignmentList.value = it
        }
    }
}