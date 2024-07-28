package com.example.mobile_final.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_final.dao.ActivityDao
import com.example.mobile_final.entity.Activity
import kotlinx.coroutines.launch

class ActivityViewModel(private val activityDao: ActivityDao) : ViewModel() {

    var imagePath = MutableLiveData<Uri>();

    fun getActivity(): LiveData<List<Activity>> {
        return activityDao.getAll();
    }

    fun insertActivity(userId: Int, description: String, imagePath: String?) =
        viewModelScope.launch {
            activityDao.insert(Activity(0, userId, description, imagePath))
        }
}