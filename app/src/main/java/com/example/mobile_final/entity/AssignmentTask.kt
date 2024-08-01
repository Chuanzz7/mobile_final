package com.example.mobile_final.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "assignment_task")
data class AssignmentTask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "assignment_id")
    var assignmentId: Int,
    @ColumnInfo(name = "name")
    var name: String
)