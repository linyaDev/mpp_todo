package org.linya.todo.multiplatform.ui.todo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.linya.utils.ui.todo.models.TodoModel
import org.linya.todo.multiplatform.R

class HeaderAdapterDelegate : AbsListItemAdapterDelegate<TodoModel.TodoModelHeader,TodoModel,HeaderAdapterDelegate.HeaderViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_task_header , parent, false)
        return HeaderViewHolder(view)
    }

    override fun isForViewType(item: TodoModel, items: MutableList<TodoModel>, position: Int): Boolean {
        return item is TodoModel.TodoModelHeader
    }

    override fun onBindViewHolder(item: TodoModel.TodoModelHeader, holder: HeaderViewHolder, payloads: MutableList<Any>) {

    }

    inner class HeaderViewHolder(view :View): RecyclerView.ViewHolder(view){

        init {

        }


    }
}