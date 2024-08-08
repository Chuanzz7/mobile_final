package com.example.mobile_final.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.mobile_final.entity.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subject ORDER BY enrolled")
    fun getAll(): LiveData<List<Subject>>

    @Query("SELECT * FROM subject WHERE id = :id")
    fun findById(id: Int): Flow<Subject?>

    @Update
    suspend fun update(subject: Subject)

    @Query("SELECT count(*) FROM subject WHERE subject.enrolled = 1")
    fun calculateActive(): LiveData<Int>
}