package com.linya.utils.ui.todo

import com.linya.utils.interfaces.RouterDependencies
import com.linya.utils.interfaces.ViewType
import com.linya.utils.mvi.Storage
import com.linya.utils.router.Router


interface TodoRouterActions{
    fun openMenu()
    fun closeMenu()
    fun openAddTask()
    fun closeAddTask()
    fun openFilter(){}
    fun closeFiler(){}
}

class TodoRouter(private val dependencies: RouterDependencies): Router(dependencies), TodoRouterActions{

    private lateinit var mainStorage : TodoMainStorage
    private var menuStorage : TodoMenuStorage? = null
    private var addTaskStorage : TodoAddTaskStorage? = null

    init {

        val storageView = dependencies.viewCreator().createView(TodoViewTypes.TodoTable)  as? TodoMainRenderView
        if (storageView!=null) {
            mainStorage = TodoMainStorage(storageView,this)
            storageView.setupPresenter(mainStorage)
            addInteractor(mainStorage)
        }
    }

    override fun openMenu(){
        if (menuStorage!=null)
            return

        val storageView = dependencies.viewCreator().createView(TodoViewTypes.TodoMenu) as? TodoMenuRenderView
        if (storageView!=null) {
            val interactor = TodoMenuStorage(storageView,this)
            storageView.setupPresenter(interactor)
            addInteractor(interactor)
            menuStorage = interactor
        }
    }

    override fun closeMenu() {
        menuStorage?.let {
            removeInteractor(it)
        }
        menuStorage = null
    }

    override fun openAddTask() {
        if (addTaskStorage!=null)
            return

        val storageView = dependencies.viewCreator().createView(TodoViewTypes.TodoAddTask) as? TodoAddTaskRenderView
        if (storageView!=null) {
            val interactor = TodoAddTaskStorage(storageView,this,mainStorage)
            storageView.setupPresenter(interactor)
            addInteractor(interactor)
            addTaskStorage = interactor
        }
    }

    override fun closeAddTask() {
        addTaskStorage?.let {
            removeInteractor(it)
        }
        addTaskStorage = null
    }
}