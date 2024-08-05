package com.example.mobile_final.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mobile_final.entity.Assignment
import com.example.mobile_final.entity.Subject

data class AssignmentSubject(
    @Embedded
    val assignment: Assignment,
    @Relation(
        parentColumn = "subject_id",
        entityColumn = "id"
    )
    val subject: Subject
)