package com.linya.utils.ui.todo

import com.linya.utils.interfaces.RenderView
import com.linya.utils.mvi.Storage

sealed class TodoModel{
    data class TodoModelHeader(val title: String= "Title"): TodoModel()
    data class TodoModelNote(val description: String= "",val text: String= ""): TodoModel()
}

class TodoStorage(renderView : RenderView<TodoWish, TodoState, Nothing>): Storage<TodoStorage.TodoWish, TodoStorage.TodoState, TodoStorage.TodoEffect>(
    renderView = renderView,
    initState = TodoState(todoList = listOf(TodoModel.TodoModelHeader("My tasks"))),
    actor = TodoActor(),
    reducer = TodoReducer()
){

    sealed class TodoWish{
        object ShowMenu : TodoWish()
        object ShowFilter : TodoWish()
        object AddTodo: TodoWish()
        data class RemoveTodo(val todo: TodoModel): TodoWish()
    }

    data class TodoState(val name: String = "",
                         val todoList: List<TodoModel> = listOf())

    sealed class TodoEffect{
        data class AddTodo(val todo: TodoModel): TodoEffect()
    }

    class TodoActor: Actor<TodoWish, TodoState, TodoEffect>{
        override fun invoke(state: TodoState, wish: TodoWish, channel: Emitter<TodoEffect>) {
            when(wish){
                else -> channel.send(TodoEffect.AddTodo(TodoModel.TodoModelNote("TestModel","This is test model")))
            }
        }
    }

    class TodoReducer: Reducer<TodoState, TodoEffect>{
        override fun reduce(state: TodoState, effect: TodoEffect): TodoState {
            return state.copy()
        }
    }

}