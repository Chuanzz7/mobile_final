package com.example.mobile_final.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobile_final.dao.ActivityDao
import com.example.mobile_final.dao.SubjectDao
import com.example.mobile_final.entity.Activity
import com.example.mobile_final.entity.Subject
import com.example.mobile_final.entity.User

@Database(
    entities = [User::class, Activity::class, Subject::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDao
    abstract fun subjectDao(): SubjectDao

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
                        .build()
                }
                return instance
            }
        }
    }
}