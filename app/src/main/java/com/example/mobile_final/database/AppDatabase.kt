package com.example.mobile_final.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobile_final.dao.ActivityDao
import com.example.mobile_final.dao.AssignmentDao
import com.example.mobile_final.dao.SubjectDao
import com.example.mobile_final.dao.TaskDao
import com.example.mobile_final.entity.Activity
import com.example.mobile_final.entity.Assignment
import com.example.mobile_final.entity.Task
import com.example.mobile_final.entity.Subject
import com.example.mobile_final.entity.User
import com.example.mobile_final.entity.converter.SubjectConverter

@Database(
    entities = [User::class, Activity::class, Subject::class, Assignment::class, Task::class],
    version = 1,
)
@TypeConverters(SubjectConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDao
    abstract fun subjectDao(): SubjectDao
    abstract fun assignmentDao(): AssignmentDao
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app"

                    )
                        .createFromAsset("app.db")
                        .build()
                }
                return instance
            }
        }
    }
}