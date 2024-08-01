package com.example.mobile_final.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_final.dao.SubjectDao
import kotlinx.coroutines.launch

class SubjectViewModel(private val subjectDao: SubjectDao) : ViewModel() {

//    fun findById(id: Int) = viewModelScope.launch {
//        val data = subjectDao.findById(id).collect { x ->
//            if (x != null) {
//                println(x.subjectDetails)
//            }
//        }
//    }


    val liveDetails: MutableLiveData<ArrayList<HashMap<String, String>>> = MutableLiveData();

    fun findById(id: Int) = viewModelScope.launch {
        val list = ArrayList<HashMap<String, String>>()
        subjectDao.findById(id)
            .collect { x ->
                x?.subjectDetails?.forEach {
                    it["expanded"] = "false"
                    list.add(it)
                }
                liveDetails.postValue(list)
            }
    }
}