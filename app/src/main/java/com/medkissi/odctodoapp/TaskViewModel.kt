package com.medkissi.odctodoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medkissi.odctodoapp.database.Task
import com.medkissi.odctodoapp.database.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel : ViewModel() {
    val dao = TaskDatabase.getTaskDaoInstance(App.getAppContext())

    val tasks:LiveData<List<Task>> = dao.getTasks()

    fun insertTask(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.insertTask(task)
            }

        }

    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.updateTask(task)
            }

        }

    }

    fun onTaskCompleted(task:Task,isCompleted:Boolean){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                dao.updateTask(task.copy(isCompleted = isCompleted))
            }
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                dao.deleteAll()
            }
        }
    }

    fun deleteTask(task:Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                dao.deleteTask(task)
            }
        }
    }


}