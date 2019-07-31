package ui.login

import ribs.*
import ui.root.OSSpecificDependencies
import utils.ui.Screen
import utils.ui.login.TodoStorage
import kotlin.reflect.KClass

interface LoginStorageView : RenderView<TodoStorage.TodoWish,TodoStorage.TodoState>
typealias LoginView  = Screen<TodoStorage.TodoWish,TodoStorage.TodoState>

class LoginBuilder {
    fun build(dependencies: OSSpecificDependencies): LoginRouter {
        return LoginRouter(dependencies)
    }
}

class LoginRouter(dependencies: OSSpecificDependencies) :
        Router(dependencies){

    init {
        val view = dependencies.createView(Screen.ScreenType.Login) as LoginView
        val renderView = view.renderView as LoginStorageView
        val interactor = TodoStorage(view)
        renderView.setupPresenter(interactor)

        addInteractor(interactor)
    }

    override fun getScreen(): Screen.ScreenType {
        return Screen.ScreenType.Login
    }
}