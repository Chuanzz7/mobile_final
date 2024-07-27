package com.example.mobile_final.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_final.dao.ActivityDao
import com.example.mobile_final.entity.Activity

class ActivityViewModel(private val activityDao: ActivityDao) : ViewModel() {

    fun getActivity(): LiveData<List<Activity>> {
        return activityDao.getAll();
    }
}