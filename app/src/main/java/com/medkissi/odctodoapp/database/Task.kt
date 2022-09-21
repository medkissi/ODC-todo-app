package com.medkissi.odctodoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int= 0,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "is_completed")
    val isCompleted:Boolean)
