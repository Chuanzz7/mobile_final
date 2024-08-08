package com.example.mobile_final.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.mobile_final.dto.AssignmentSubject
import com.example.mobile_final.entity.Assignment
import kotlinx.coroutines.flow.Flow

@Dao
interface AssignmentDao {

    @Query("SELECT * FROM assignment")
    fun findAll(): LiveData<List<Assignment>>;

//    @Transaction
//    @Query("SELECT a.id as id , a.name as name , a.description as description, a.deliverables as deliverables , " +
//            "a.dueDate as dueDate , a.submitted as submitted, a.weightage as weightabe FROM assignment a INNER JOIN subject s_ ON a.subject_id = s_.id WHERE s_.enrolled = 1 ")
//    fun findAllEnrolled(): LiveData<List<AssignmentSubject>>;

    @Transaction
    @Query("SELECT assignment.* FROM assignment INNER JOIN subject s_ ON subject_id = s_.id WHERE s_.enrolled = 1 ORDER BY submitted  ")
    fun findAllEnrolled(): LiveData<List<AssignmentSubject>>;

    @Query("SELECT * FROM assignment WHERE submitted = 0")
    fun findAllTemp(): Flow<List<Assignment>>;

    @Query("UPDATE assignment SET submitted = 1 WHERE id = :id ")
    suspend fun submitAssignmentById(id: Int)
}