package com.linya.utils.ui.todo

import com.linya.utils.interfaces.Builder
import com.linya.utils.interfaces.RenderView
import com.linya.utils.interfaces.RouterDependencies
import com.linya.utils.interfaces.ScreenType
import com.linya.utils.mvi.StorageView
import com.linya.utils.ribs.Router
import com.linya.utils.ui.Screens

interface TodoStorageView : RenderView<TodoStorage.TodoWish,TodoStorage.TodoState>
typealias TodoView  = StorageView<TodoStorage.TodoWish, TodoStorage.TodoState>

class TodoRouter(dependencies: RouterDependencies): Router(dependencies){

    init {
        val storageView = dependencies.viewCreator().createView(TodoViewTypes.TodoTable)
        val view = storageView as? TodoView
        if (view!=null) {
            val renderView = view.renderView as TodoStorageView
            val interactor = TodoStorage(view)
            renderView.setupPresenter(interactor)
            addInteractor(interactor)
        }
    }
}