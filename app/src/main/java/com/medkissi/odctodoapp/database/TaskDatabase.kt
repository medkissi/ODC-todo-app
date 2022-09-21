package com.medkissi.odctodoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], exportSchema = false, version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO

    //
    companion object {
        @Volatile
        private var INSTANCE: TaskDAO? = null

        fun getTaskDaoInstance(context: Context): TaskDAO {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = buildDatabase(context).taskDao()
                    INSTANCE = instance
                }
                return instance
            }


        }


        private fun buildDatabase(context: Context): TaskDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "task_database"
            ).fallbackToDestructiveMigration()
                .build()


        }
    }
}