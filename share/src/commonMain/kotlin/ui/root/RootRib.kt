package ui.root

import ribs.*
import ui.login.LoginBuilder
import utils.ui.Screen


class RootBuilder(private val dependencies: OSSpecificDependencies) {

    fun build(): RootRouter {
        val view = RootViewProvider(dependencies).getView()
        return RootRouter(view, dependencies)
    }
}


class RootRouter(view: RootView,private val dependencies: OSSpecificDependencies) : Router(dependencies) {
    val rootView: RootView = view

    init {

    }

    private fun showTodo() {

    }

    override fun activate() {
        if (child == null){
            val router = LoginBuilder().build(dependencies)
            attach(router)
        }

        super.activate()
    }

    override fun didAttach() {
       //showTodo()
    }

    override fun getScreen(): Screen.ScreenType {
        return Screen.ScreenType.Root
    }
}

expect class RootView : RenderView<Any,Any>

expect class RootViewProvider(dependencies: OSSpecificDependencies) {
    fun getView(): RootView
}

expect interface OSSpecificDependencies : ViewCreator,ViewNavigator