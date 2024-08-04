package com.example.mobile_final.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "assignment_id")
    var assignmentId: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "dueDate")
    var dueDate: Date,
    @ColumnInfo(name = "completed")
    var completed: Boolean = false,
    @ColumnInfo(name = "completed_time")
    var completed_time: Date? = null
)