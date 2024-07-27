package com.example.mobile_final.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_final.dao.SubjectDao
import com.example.mobile_final.entity.Subject

class SubjectListViewModel(private val subjectDao: SubjectDao) : ViewModel() {

    fun getAll(): LiveData<List<Subject>> {
        return subjectDao.getAll();
    }
}