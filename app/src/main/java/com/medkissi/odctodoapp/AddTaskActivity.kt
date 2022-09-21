package com.medkissi.odctodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val title = findViewById<EditText>(R.id.add_activity_title)
        val btnAjouter = findViewById<Button>(R.id.btn_ajouter)
        btnAjouter.setOnClickListener {
            intent.putExtra("title",title.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }



    }
}