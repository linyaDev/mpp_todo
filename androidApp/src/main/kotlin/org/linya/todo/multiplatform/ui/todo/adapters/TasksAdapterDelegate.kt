package org.linya.todo.multiplatform.ui.todo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.linya.utils.ui.todo.models.TodoModel
import org.linya.todo.multiplatform.R

class TasksAdapterDelegate : AbsListItemAdapterDelegate<TodoModel.TodoModelHeader,TodoModel,TasksAdapterDelegate.TaskViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_task , parent, false)
        return TaskViewHolder(view)
    }

    override fun isForViewType(item: TodoModel, items: MutableList<TodoModel>, position: Int): Boolean {
        return item is TodoModel.TodoModelNote
    }

    override fun onBindViewHolder(item: TodoModel.TodoModelHeader, holder: TaskViewHolder, payloads: MutableList<Any>) {

    }

    inner class TaskViewHolder(view :View): RecyclerView.ViewHolder(view){

        init {

        }


    }
}