package com.example.mobile_final.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mobile_final.entity.Assignment
import kotlinx.coroutines.flow.Flow

@Dao
interface AssignmentDao {

    @Query("SELECT * FROM assignment")
    fun findAll(): LiveData<List<Assignment>>;

    @Query("SELECT * FROM assignment")
    fun findAllTemp(): Flow<List<Assignment>>;
}