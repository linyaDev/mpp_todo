package com.linya.utils.ui.todo

import com.linya.utils.interfaces.RenderView
import com.linya.utils.mvi.Storage

typealias TodoMenuRenderView = RenderView<TodoMenuStorage.TodoMenuWish, TodoMenuStorage.TodoMenuState,TodoMenuStorage.TodoMenuNews>

class TodoMenuStorage(renderView : RenderView<TodoMenuWish, TodoMenuState,TodoMenuNews>, router: TodoRouterActions):
        Storage<TodoMenuStorage.TodoMenuWish, TodoMenuStorage.TodoMenuState, TodoMenuStorage.TodoMenuEffect,TodoMenuStorage.TodoMenuNews>(
    renderView = renderView,
    initState = TodoMenuState(),
    actor = TodoMenuActor(router),
    reducer = TodoReducer()
){

    sealed class TodoMenuWish{
        object CloseMenu : TodoMenuWish()
    }
    sealed class TodoMenuNews{}
    data class TodoMenuState(val name: String = "")
    sealed class TodoMenuEffect{}

    class TodoMenuActor(private val router: TodoRouterActions): Actor<TodoMenuWish, TodoMenuState, TodoMenuEffect>{
        override fun invoke(state: TodoMenuState, wish: TodoMenuWish, channel: Emitter<TodoMenuEffect>) {
            when(wish){
                is TodoMenuWish.CloseMenu -> router.closeMenu()
                //else -> channel.send(TodoMenuEffect.AddTask(TodoModel.TodoModelNote("TestModel","This is test model")))
            }
        }
    }

    class TodoReducer: Reducer<TodoMenuState, TodoMenuEffect>{
        override fun reduce(state: TodoMenuState, effect: TodoMenuEffect): TodoMenuState {
            return state.copy()
        }
    }

}