package com.example.mobile_final.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_final.dao.SubjectDao
import kotlinx.coroutines.launch

class SubjectViewModel(private val subjectDao: SubjectDao) : ViewModel() {

    fun findById(id: Int) = viewModelScope.launch {
        val data = subjectDao.findById(id).collect { x ->
            if (x != null) {
                println(x.subjectDetails)
            }
        }
    }
}