package org.linya.todo.multiplatform.ui.todo.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.linya.utils.mvi.Store
import com.linya.utils.ui.todo.TodoAddTaskStorage
import com.linya.utils.ui.todo.TodoMainStorage
import com.linya.utils.ui.todo.models.TodoModel

class TasksAdapter(storage: Store<TodoMainStorage.TodoWish, TodoMainStorage.TodoState>): AsyncListDifferDelegationAdapter<TodoModel>(TodoAsyncDiffUtilsCallback()){

    init {
        delegatesManager.addDelegate(HeaderAdapterDelegate())
        delegatesManager.addDelegate(TasksAdapterDelegate(storage))
        items = listOf()
    }

}