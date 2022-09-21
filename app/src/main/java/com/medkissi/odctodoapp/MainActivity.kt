package com.medkissi.odctodoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.medkissi.odctodoapp.database.Task

class MainActivity() : AppCompatActivity(), TaskAdapter.OnTaskClickListner {
    private val viewModel:TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this,AddTaskActivity::class.java)
            startActivityForResult(intent,2)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TaskAdapter(this)
        recyclerView.adapter = adapter

        viewModel.tasks.observe(this){
            adapter.submitList(it)

        }

       val itemTouchCallBack = object:ItemTouchHelper.SimpleCallback(0,
           ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
       {
           override fun onMove(
               recyclerView: RecyclerView,
               viewHolder: RecyclerView.ViewHolder,
               target: RecyclerView.ViewHolder
           ): Boolean {
              return  true
           }

           override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               val position = viewHolder.adapterPosition
               val task = adapter.getTaskAtPosition(position)
               viewModel.deleteTask(task)
           }

       }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallBack)
            itemTouchHelper.attachToRecyclerView(recyclerView)



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == RESULT_OK){
            val title = data?.getStringExtra("title")
            val task = Task(title =title.toString() , isCompleted = true)
            viewModel.insertTask(task)
        }


    }

    override fun onTaskClick(task: Task) {
        val intent = Intent(this,AddTaskActivity::class.java)
        intent.putExtra("id",task.id)
        intent.putExtra("title",task.title)
        startActivityForResult(intent,3)
    }

    override fun onCheckBoxClick(task: Task, isChecked: Boolean) {
        viewModel.onTaskCompleted(task,isChecked)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId ==R.id.item_delete_all){
            viewModel.deleteAll()

        }
        return super.onOptionsItemSelected(item)
    }
}