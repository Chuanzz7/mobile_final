package com.example.mobile_final.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "assignment")
data class Assignment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "subject_id")
    var subjectId: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "dueDate")
    var dueDate: Date,
    @ColumnInfo(name = "submitted")
    var submitted: Boolean = false
)