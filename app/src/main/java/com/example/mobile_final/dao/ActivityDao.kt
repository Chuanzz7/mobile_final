package com.example.mobile_final.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobile_final.entity.Activity

@Dao
interface ActivityDao {

    @Query("SELECT * FROM activity")
    fun getAll(): LiveData<List<Activity>>

    @Insert
    suspend fun insert(activity: Activity)
}