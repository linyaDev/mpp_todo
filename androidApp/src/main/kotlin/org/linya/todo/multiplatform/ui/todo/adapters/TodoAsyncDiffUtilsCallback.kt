package org.linya.todo.multiplatform.ui.todo.adapters

import androidx.recyclerview.widget.DiffUtil
import com.linya.utils.ui.todo.models.TodoModel


class TodoAsyncDiffUtilsCallback : DiffUtil.ItemCallback<TodoModel>() {

    override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return false//oldItem == newItem
    }

}