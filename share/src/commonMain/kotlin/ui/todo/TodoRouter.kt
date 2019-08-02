package com.linya.utils.ui.todo

import com.linya.utils.interfaces.RenderView
import com.linya.utils.interfaces.RouterDependencies
import com.linya.utils.mvi.Store
import com.linya.utils.router.Router

typealias TodoStorageView = RenderView<TodoStorage.TodoWish,TodoStorage.TodoState,TodoStorage.TodoNews>
typealias TodoView  = RenderView<TodoStorage.TodoWish, TodoStorage.TodoState,TodoStorage.TodoNews>

class TodoRouter(dependencies: RouterDependencies): Router(dependencies){

    init {
        val storageView = dependencies.viewCreator().createView(TodoViewTypes.TodoTable)
        val view = storageView as? TodoView
        if (view!=null) {
            val interactor = TodoStorage(view)
            view.setupPresenter(interactor)
            addInteractor(interactor)
        }
    }
}