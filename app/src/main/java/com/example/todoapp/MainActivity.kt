package com.example.todoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter<Any?>
    private var taskList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskAdapter = TaskAdapter(taskList) { task ->
            // Abre la actividad de detalles o edición
            openTaskDetails(task)
        }

        findViewById<RecyclerView>(R.id.todoRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        findViewById<FloatingActionButton>(R.id.addTaskButton).setOnClickListener {
            openTaskForm()
        }
    }

    private fun openTaskDetails(task: Task) {
        // Implementa la navegación a la pantalla de detalles de la tarea
    }

    private fun openTaskForm() {
        // Implementa la navegación para agregar una nueva tarea
    }
}
