package com.linya.utils.ui.todo

import com.linya.utils.interfaces.StorageView
import com.linya.utils.mvi.Storage


class TodoStorage(storageView : StorageView<TodoWish, TodoState>): Storage<TodoStorage.TodoWish, TodoStorage.TodoState, TodoStorage.TodoEffect>(
    storageView = storageView,
    initState = TodoState(""),
    actor = TodoActor(),
    reducer = TodoReducer()
){

    sealed class TodoWish{
        object ShowMenu : TodoWish()
        object AddTodo: TodoWish()
    }

    data class TodoModel(val title: String= "Title")

    data class TodoState(val name: String = "",
                         val todoList: List<TodoModel> = listOf())

    sealed class TodoEffect{
        data class setName(val x:Int = 3): TodoEffect()
    }

    class TodoActor: Actor<TodoWish, TodoState, TodoEffect>{
        override fun invoke(state: TodoState, wish: TodoWish, channel: Emitter<TodoEffect>) {
            when(wish){
                else -> channel.send(TodoEffect.setName(5))
            }
        }
    }

    class TodoReducer: Reducer<TodoState, TodoEffect>{
        override fun reduce(state: TodoState, effect: TodoEffect): TodoState {
            return state.copy(todoList = listOf(TodoModel()))
        }
    }

}