package com.linya.utils.ui.todo

import com.linya.utils.interfaces.RenderView
import com.linya.utils.mvi.Storage
import com.linya.utils.ui.todo.models.TodoModel

typealias TodoAddTaskRenderView = RenderView<TodoAddTaskStorage.TodoAddWish, TodoAddTaskStorage.TodoAddState,TodoAddTaskStorage.TodoAddNews>

class TodoAddTaskStorage(renderView : RenderView<TodoAddWish, TodoAddState,TodoAddNews>,
                         router: TodoRouterActions,
                         storage: TodoMainStorage):
        Storage<TodoAddTaskStorage.TodoAddWish, TodoAddTaskStorage.TodoAddState, TodoAddTaskStorage.TodoAddEffect,TodoAddTaskStorage.TodoAddNews>(
    renderView = renderView,
    initState = TodoAddState(),
    actor = TodoMenuActor(router,storage),
    reducer = TodoReducer()
){

    sealed class TodoAddWish{
        object CloseAddTaskMenu : TodoAddWish()
        data class AddTask(val todo: TodoModel): TodoAddWish()
    }

    sealed class TodoAddNews{}
    data class TodoAddState(val name: String = "")
    sealed class TodoAddEffect{}

    class TodoMenuActor(private val router: TodoRouterActions,
                        private val mainStorage: TodoMainStorage): Actor<TodoAddWish, TodoAddState, TodoAddEffect>{
        override fun invoke(state: TodoAddState, wish: TodoAddWish, channel: Emitter<TodoAddEffect>) {
            when(wish){
                is TodoAddWish.CloseAddTaskMenu -> router.closeAddTask()
                is TodoAddWish.AddTask -> {
                    mainStorage.accept(TodoMainStorage.TodoWish.AddTask(wish.todo))
                }
            }
        }
    }

    class TodoReducer: Reducer<TodoAddState, TodoAddEffect>{
        override fun reduce(state: TodoAddState, effect: TodoAddEffect): TodoAddState {
            return state.copy()
        }
    }

}