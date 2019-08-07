package com.linya.utils.ui.todo

import com.linya.utils.interfaces.RenderView
import com.linya.utils.mvi.Storage
import com.linya.utils.ui.todo.models.TodoModel

typealias TodoMainRenderView = RenderView<TodoMainStorage.TodoWish,TodoMainStorage.TodoState,TodoMainStorage.TodoNews>

class TodoMainStorage(renderView : RenderView<TodoWish, TodoState,TodoNews>, router: TodoRouterActions):
        Storage<TodoMainStorage.TodoWish, TodoMainStorage.TodoState, TodoMainStorage.TodoEffect,TodoMainStorage.TodoNews>(
    renderView = renderView,
    initState = TodoState(todoList = listOf(TodoModel.TodoModelHeader("My tasks"))),
    actor = TodoActor(router),
    reducer = TodoReducer()
){

    sealed class TodoWish{
        object ShowMenu : TodoWish()
        object ShowFilter : TodoWish()
        object AddTask: TodoWish()
        data class RemoveTodo(val todo: TodoModel): TodoWish()
    }
    sealed class TodoNews{

    }

    data class TodoState(val name: String = "",
                         val todoList: List<TodoModel> = listOf())

     sealed class TodoEffect{
        data class AddTodo(val todo: TodoModel): TodoEffect()
    }

    class TodoActor(private val router: TodoRouterActions): Actor<TodoWish, TodoState, TodoEffect>{
        override fun invoke(state: TodoState, wish: TodoWish, channel: Emitter<TodoEffect>) {
            when(wish){
                is TodoWish.ShowMenu -> router.openMenu()
                is TodoWish.ShowFilter -> router.openMenu()
                is TodoWish.AddTask -> router.openMenu()
                else -> channel.send(TodoEffect.AddTodo(TodoModel.TodoModelNote("TestModel","This is test model")))
            }
        }
    }

    class TodoReducer: Reducer<TodoState, TodoEffect>{
        override fun reduce(state: TodoState, effect: TodoEffect): TodoState {
            return state
        }
    }

}