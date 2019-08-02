package com.linya.utils.ui.todo

import com.linya.utils.interfaces.RenderView
import com.linya.utils.interfaces.RouterDependencies
import com.linya.utils.interfaces.StorageView
import com.linya.utils.ribs.Router

typealias TodoStorageView = RenderView<TodoStorage.TodoWish,TodoStorage.TodoState>
typealias TodoView  = StorageView<TodoStorage.TodoWish, TodoStorage.TodoState>

class TodoRouter(dependencies: RouterDependencies): Router(dependencies){

    init {
        val storageView = dependencies.viewCreator().createView(TodoViewTypes.TodoTable)
        val view = storageView as? TodoView
        if (view!=null) {
            val renderView = view.renderView
            val interactor = TodoStorage(view)
            renderView.setupPresenter(interactor)
            addInteractor(interactor)
        }
    }
}