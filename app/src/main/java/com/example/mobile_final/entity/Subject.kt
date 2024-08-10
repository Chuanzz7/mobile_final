package com.example.mobile_final.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "code")
    var code: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "student_amount")
    var studentAmount: Int,
    @ColumnInfo(name = "credit_hours")
    var creditHours: Int,
    @ColumnInfo(name = "year")
    var year: Int,
    @ColumnInfo(name = "details")
    var subjectDetails: List<HashMap<String, String>>,
    @ColumnInfo(name = "enrolled")
    var enrolled: Boolean = false
)