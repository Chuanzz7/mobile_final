package com.example.mobile_final.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_final.dao.AssignmentDao
import com.example.mobile_final.dao.SubjectDao
import com.example.mobile_final.dao.TaskDao
import com.example.mobile_final.dto.HomeTask
import com.example.mobile_final.entity.Assignment
import com.example.mobile_final.entity.Task
import kotlinx.coroutines.launch

class HomeViewModel(
    private val taskDao: TaskDao,
    private val assignmentDao: AssignmentDao,
    private val subjectDao: SubjectDao
) : ViewModel() {
    var taskAssignment: MutableLiveData<List<HomeTask>> = MutableLiveData()

    fun taskCount(): LiveData<Int> {
        return taskDao.calculateActive();
    }

    fun assignmentCount(): LiveData<Int> {
        return assignmentDao.findAllEnrolledCount();
    }

    fun subjectCount(): LiveData<Int> {
        return subjectDao.calculateActive();
    }

    fun findRecentAssignment(): LiveData<List<Assignment>> {
        return assignmentDao.findRecent();
    }

    fun findRecentTask(): LiveData<List<Task>> {
        return taskDao.findRecent();
    }

    fun findMyProgress() = viewModelScope.launch {
        val list = HashMap<Int, HomeTask>()

        taskDao.findAllAssignmentTask().collect { x ->
            x.forEach { y ->
                if (list[y.assignment.id] == null) {
                    list[y.assignment.id] =
                        HomeTask(y.assignment.name, if (y.task.completed) 1.0 else 0.0, 1.0)
                } else {
                    list[y.assignment.id]?.taskTotalCount =
                        list[y.assignment.id]?.taskTotalCount?.plus(
                            1
                        )!!

                    if (y.task.completed) {
                        list[y.assignment.id]?.taskCompletedCount =
                            list[y.assignment.id]?.taskCompletedCount?.plus(1)!!
                    }
                }
            }

            taskAssignment.value = list.values.toList()
        }
    }
}