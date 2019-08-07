package org.linya.todo.multiplatform.ui.todo.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.linya.utils.ui.todo.models.TodoModel

class TasksAdapter: ListDelegationAdapter<List<TodoModel>>(){

    init {
        delegatesManager.addDelegate(HeaderAdapterDelegate())
        delegatesManager.addDelegate(TasksAdapterDelegate())
    }

}