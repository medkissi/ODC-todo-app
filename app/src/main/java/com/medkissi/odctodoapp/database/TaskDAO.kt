package com.medkissi.odctodoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {

    @Query("SELECT * FROM tasks")
     fun getTasks(): LiveData<List<Task>>

    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
    @Query("DELETE FROM tasks")
    suspend fun deleteAll()
}