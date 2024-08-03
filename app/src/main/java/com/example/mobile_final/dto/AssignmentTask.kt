package com.example.mobile_final.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mobile_final.entity.Assignment
import com.example.mobile_final.entity.Task

data class AssignmentTask(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "assignment_id",
        entityColumn = "id"
    )
    val assignment: Assignment
)