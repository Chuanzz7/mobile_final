package com.example.mobile_final.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_final.dao.TaskDao
import com.example.mobile_final.dto.AssignmentTask
import com.example.mobile_final.entity.Task
import kotlinx.coroutines.launch
import java.util.Date

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    fun insert(assignmentId: Int, name: String, dueDate: Date) = viewModelScope.launch {
        taskDao.insert(Task(0, assignmentId, name, dueDate))
    }

    fun update(task: Task) = viewModelScope.launch {
        taskDao.updateCompleted(task)
    }

    fun findAllIncomplete(): LiveData<List<AssignmentTask>> {
        return taskDao.findAllAssignmentTaskIncomplete()
    }

    fun findAllCompleted(): LiveData<List<AssignmentTask>> {
        return taskDao.findAllAssignmentTaskCompleted()
    }

    fun updateAllAssignment(assignmentId: Int) = viewModelScope.launch {
        taskDao.updateAllByAssignmentId(assignmentId, Date().time)
    }
}