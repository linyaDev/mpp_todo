package org.linya.todo.multiplatform.ui.todo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.linya.utils.mvi.Store
import com.linya.utils.ui.todo.TodoMainStorage
import com.linya.utils.ui.todo.models.TodoModel
import org.linya.todo.multiplatform.R

class TasksAdapterDelegate(val storage: Store<TodoMainStorage.TodoWish, TodoMainStorage.TodoState>) : AbsListItemAdapterDelegate<TodoModel.TodoModelNote,TodoModel,TasksAdapterDelegate.TaskViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_task , parent, false)
        return TaskViewHolder(view)
    }

    override fun isForViewType(item: TodoModel, items: MutableList<TodoModel>, position: Int): Boolean {
        return item is TodoModel.TodoModelNote
    }

    override fun onBindViewHolder(item: TodoModel.TodoModelNote, holder: TaskViewHolder, payloads: MutableList<Any>) {
        holder.taskTitle.text = item.title

        val descText  = item.text
        if (descText.isNotEmpty()){
            holder.taskDescription.visibility = View.VISIBLE
            holder.taskDescription.text = descText
        }else {
            holder.taskDescription.visibility = View.GONE
        }

        holder.checkbox.isChecked = false
        holder.checkbox.setOnClickListener {
            storage.accept(TodoMainStorage.TodoWish.RemoveTodo(item))
        }
    }

    inner class TaskViewHolder(view :View): RecyclerView.ViewHolder(view){
        val taskTitle: TextView = view.findViewById(R.id.title)
        val taskDescription: TextView  = view.findViewById(R.id.description)
        val checkbox: CheckBox = view.findViewById(R.id.checkbox)
    }
}