package utils.ui.login

import mvi.Storage
import utils.ui.Screen

class TodoStorage(screen : Screen<TodoWish, TodoState>): Storage<TodoStorage.TodoWish,TodoStorage.TodoState,TodoStorage.TodoEffect>(
    screen = screen,
    initState = TodoState(""),
    actor = TodoActor(),
    reducer = TodoReducer()
){

    sealed class TodoWish{
        object ShowMenu : TodoWish()
        object AddTodo: TodoWish()
    }

    data class TodoState(val name: String = "",
                         val todoList: List<Int> = listOf())

    sealed class TodoEffect{
        data class setName(val x:Int = 3): TodoEffect()
    }

    class TodoActor: Actor<TodoWish,TodoState,TodoEffect>{
        override fun invoke(state: TodoState, wish: TodoWish, channel: Emitter<TodoEffect>) {
            when(wish){
                else -> channel.send(TodoEffect.setName(5))
            }
        }
    }

    class TodoReducer: Reducer<TodoState,TodoEffect>{
        override fun reduce(state: TodoState, effect: TodoEffect): TodoState {
            return state.copy(todoList = listOf(3,4,6))
        }
    }

}