package org.linya.todo.multiplatform.ui.todo.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.linya.utils.ui.todo.models.TodoModel

class TasksAdapter: AsyncListDifferDelegationAdapter<TodoModel>(TodoAsyncDiffUtilsCallback()){

    init {
        delegatesManager.addDelegate(HeaderAdapterDelegate())
        delegatesManager.addDelegate(TasksAdapterDelegate())
        items = listOf()
    }

}