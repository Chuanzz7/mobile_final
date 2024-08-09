package com.example.mobile_final.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mobile_final.dto.AssignmentTask
import com.example.mobile_final.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM task ORDER by assignment_id, id")
    fun findAll(): LiveData<List<Task>>

    @Query("SELECT * FROM task")
    fun findAllAssignmentTask(): Flow<List<AssignmentTask>>

    @Query("SELECT * FROM task WHERE task.completed = 0 ORDER by dueDate asc ")
    fun findAllAssignmentTaskIncomplete(): LiveData<List<AssignmentTask>>

    @Query("SELECT * FROM task WHERE task.completed = 1")
    fun findAllAssignmentTaskCompleted(): LiveData<List<AssignmentTask>>

    @Update
    suspend fun updateCompleted(task: Task)

    @Query("UPDATE task SET completed = 1, completed_time = :completed_time  WHERE assignment_id = :assignmentId")
    suspend fun updateAllByAssignmentId(assignmentId: Int, completed_time: Long)

    @Query("SELECT count(*) FROM task WHERE task.completed = 0")
    fun calculateActive(): LiveData<Int>

    @Query("SELECT * FROM task where completed = 0 ORDER BY dueDate LIMIT 3")
    fun findRecent(): LiveData<List<Task>>
}