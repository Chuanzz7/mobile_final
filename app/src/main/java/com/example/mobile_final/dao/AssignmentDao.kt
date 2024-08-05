package com.example.mobile_final.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mobile_final.dto.AssignmentSubject
import com.example.mobile_final.entity.Assignment
import kotlinx.coroutines.flow.Flow

@Dao
interface AssignmentDao {

    @Query("SELECT * FROM assignment")
    fun findAll(): LiveData<List<Assignment>>;

    @Query("SELECT * FROM assignment a INNER JOIN subject s ON a.subject_id = s.id WHERE s.enrolled = 1 ")
    fun findAllEnrolled(): LiveData<List<AssignmentSubject>>;

    @Query("SELECT * FROM assignment")
    fun findAllTemp(): Flow<List<Assignment>>;
}