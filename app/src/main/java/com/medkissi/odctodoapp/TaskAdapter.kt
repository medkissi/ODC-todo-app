package com.medkissi.odctodoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.medkissi.odctodoapp.database.Task

class TaskAdapter (private val listner:OnTaskClickListner ): androidx.recyclerview.widget.ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffUtil()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_item,parent,false)

        return  TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }


    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.list_item_title)
        val checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)

        init {

            itemView.setOnClickListener {
                val position = adapterPosition
                if(position != -1 ){
                    val task = getItem(position)
                    listner.onTaskClick(task)
                }
            }

            checkBox.setOnClickListener {
                val position = adapterPosition
                if(position != -1 ){
                    val task = getItem(position)
                    listner.onCheckBoxClick(task,checkBox.isChecked)
                }

            }
        }

        fun bind(task: Task) {
            title.text = task.title
            title.paint.isStrikeThruText = task.isCompleted
            checkBox.isChecked = task.isCompleted

        }

    }

    class TaskDiffUtil():DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return  oldItem == newItem
        }

    }

    interface  OnTaskClickListner{
        fun onTaskClick(task:Task)
        fun onCheckBoxClick(task: Task,isChecked:Boolean)
    }

    fun getTaskAtPosition(position: Int):Task{
        val task = getItem(position)
        return task
    }

}