package com.example.mobile_final.entity.converter

import androidx.room.TypeConverter
import com.example.mobile_final.entity.SubjectDetails
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SubjectConverter {
    @TypeConverter
    fun toSubjectDetails(details: String): SubjectDetails {
        val type = object : TypeToken<SubjectDetails>() {}.type
        return Gson().fromJson(details, type)
    }

    @TypeConverter
    fun toSubjectDetailsJson(subjectDetails: SubjectDetails): String {
        val type = object : TypeToken<SubjectDetails>() {}.type
        return Gson().toJson(subjectDetails, type)
    }
}