package com.example.mobile_final.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity")
data class Activity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "user_id")
    var user_id: Int,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "image")
    var imagePath: String
)