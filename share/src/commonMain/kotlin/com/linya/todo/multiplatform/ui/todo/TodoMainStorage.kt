package com.linya.utils.ui.todo

import com.linya.utils.interfaces.RenderView
import com.linya.utils.mvi.Storage
import com.linya.utils.ui.todo.models.TodoModel

typealias TodoMainRenderView = RenderView<TodoMainStorage.TodoWish,TodoMainStorage.TodoState,TodoMainStorage.TodoNews>

class TodoMainStorage(renderView : RenderView<TodoWish, TodoState,TodoNews>, router: TodoRouterActions):
        Storage<TodoMainStorage.TodoWish, TodoMainStorage.TodoState, TodoMainStorage.TodoEffect,TodoMainStorage.TodoNews>(
    renderView = renderView,
    initState = TodoState(todoList = listOf(
     TodoModel.TodoModelHeader("My tasks")
    ,TodoModel.TodoModelNote("Task 1 ", " Detail information 1"),
     TodoModel.TodoModelNote("Task 2 ", " Detail information 2")    )),
    actor = TodoActor(router),
    reducer = TodoReducer()
){

    sealed class TodoWish{
        object ShowMenu : TodoWish()
        object ShowFilter : TodoWish()
        object ShowAddTask: TodoWish()
        data class AddTask(val todo: TodoModel): TodoWish()
        data class RemoveTodo(val todo: TodoModel): TodoWish()
    }

    sealed class TodoNews{
        data class TodoTemoved(val todo: TodoModel): TodoNews()
    }

    data class TodoState(val name: String = "",
                         val todoList: List<TodoModel> = listOf())

     sealed class TodoEffect{
        data class AddTodo(val todo: TodoModel): TodoEffect()
        data class RemoveTodo(val todo: TodoModel): TodoEffect()
    }

    class TodoActor(private val router: TodoRouterActions): Actor<TodoWish, TodoState, TodoEffect>{
        override fun invoke(state: TodoState, wish: TodoWish, channel: Emitter<TodoEffect>) {
            when(wish){
                is TodoWish.ShowMenu -> router.openMenu()
                is TodoWish.ShowFilter -> router.openFilter()
                is TodoWish.ShowAddTask -> router.openAddTask()
                is TodoWish.AddTask -> channel.send(TodoEffect.AddTodo(wish.todo))
                is TodoWish.RemoveTodo -> channel.send(TodoEffect.RemoveTodo(wish.todo))
            }
        }
    }

    class TodoReducer: Reducer<TodoState, TodoEffect>{
        override fun reduce(state: TodoState, effect: TodoEffect): TodoState {
            when(effect){
                is TodoEffect.AddTodo -> {
                    val list = state.todoList.toMutableList()
                    list.add(effect.todo)
                    return state.copy(todoList = list)
                }
                is TodoEffect.RemoveTodo -> {
                    val list = state.todoList.toMutableList()
                    list.remove(effect.todo)
                    return state.copy(todoList = list)
                }
            }
        }
    }

}