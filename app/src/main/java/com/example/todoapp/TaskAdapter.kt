package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class TaskAdapter<TextView>(private val taskList: List<Task>, private val onItemClick: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter<Any?>.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.taskTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.taskDescription)

        fun bind(task: Task) {
            titleTextView.text = task.title
            descriptionTextView.text = task.description
            itemView.setOnClickListener { onItemClick(task) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size
}
