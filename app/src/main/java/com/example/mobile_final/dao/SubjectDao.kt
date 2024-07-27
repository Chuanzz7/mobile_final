package com.example.mobile_final.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mobile_final.entity.Subject

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subject")
    fun getAll(): LiveData<List<Subject>>
}