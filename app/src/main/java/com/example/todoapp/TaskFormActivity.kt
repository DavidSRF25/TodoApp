package com.example.todoapp
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.data.model.Task

class TaskFormActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var completedCheckBox: CheckBox
    private lateinit var saveButton: Button

    private var task: Task? = null  // Tarea que se recibe para editar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        // Vincular vistas
        titleEditText = findViewById(R.id.editTextTitle)
        descriptionEditText = findViewById(R.id.editTextDescription)
        completedCheckBox = findViewById(R.id.checkBoxCompleted)
        saveButton = findViewById(R.id.buttonSaveTask)

        // Recibir datos de la tarea si estamos editando
        task = intent.getSerializableExtra("task") as? Task
        task?.let {
            titleEditText.setText(it.title)
            descriptionEditText.setText(it.description)
            completedCheckBox.isChecked = it.isCompleted
        }

        // Configurar el botón de guardar
        saveButton.setOnClickListener {
            saveTask()
        }
    }

    private fun saveTask() {
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val isCompleted = completedCheckBox.isChecked

        // Validar campos
        if (title.isBlank()) {
            titleEditText.error = "El título no puede estar vacío"
            return
        }

        // Crear o actualizar la tarea
        if (task == null) {
            task = Task(
                id = (System.currentTimeMillis() % Int.MAX_VALUE).toInt(),
                title = title,
                description = description,
                isCompleted = isCompleted
            )
        } else {
            task?.apply {
                this.title = title
                this.description = description
                this.isCompleted = isCompleted
            }
        }

        // Devolver la tarea a la actividad principal
        intent.putExtra("task_result", task)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
