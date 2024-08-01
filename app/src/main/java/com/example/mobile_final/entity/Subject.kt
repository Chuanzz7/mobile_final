package com.example.mobile_final.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "lecture_name")
    var lectureName: String,
    @ColumnInfo(name = "lecture_picture")
    var lecturePicture: String,
    @ColumnInfo(name = "details")
    var subjectDetails: List<HashMap<String, String>>
)