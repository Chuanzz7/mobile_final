package com.example.mobile_final.entity.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SubjectConverter {
    @TypeConverter
    fun toSubjectDetails(details: String): List<HashMap<String,String>> {
        val type = object : TypeToken<List<HashMap<String,String>>>() {}.type
        return Gson().fromJson(details, type)
    }

    @TypeConverter
    fun toSubjectDetailsJson(subjectDetails: List<HashMap<String,String>>): String {
        val type = object : TypeToken<List<HashMap<String,String>>>() {}.type
        return Gson().toJson(subjectDetails, type)
    }
}