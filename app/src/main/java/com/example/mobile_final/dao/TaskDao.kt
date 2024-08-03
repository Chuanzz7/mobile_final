package com.example.mobile_final.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mobile_final.dto.AssignmentTask
import com.example.mobile_final.entity.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM task ORDER by assignment_id, id")
    fun findAll(): LiveData<List<Task>>

    @Query("SELECT * FROM task ORDER by assignment_id, id DESC, completed ")
    fun findAllAssignmentTask(): LiveData<List<AssignmentTask>>

    @Update
    suspend fun updateCompleted(task: Task)
}