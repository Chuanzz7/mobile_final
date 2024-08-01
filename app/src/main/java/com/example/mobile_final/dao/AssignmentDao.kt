package com.example.mobile_final.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mobile_final.entity.Assignment

@Dao
interface AssignmentDao {

    @Query("SELECT * FROM assignment")
    fun findAll(): LiveData<List<Assignment>>;
}