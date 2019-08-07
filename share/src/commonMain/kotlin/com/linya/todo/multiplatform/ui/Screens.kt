package com.linya.utils.ui

import com.linya.utils.interfaces.RouterDependencies
import com.linya.utils.interfaces.Screen
import com.linya.utils.interfaces.ScreenType
import com.linya.utils.router.Router
import com.linya.utils.ui.todo.TodoRouter

sealed class Screens: Screen{

    sealed class ScreenTypes: ScreenType{
        object TodoScreenType: ScreenTypes()
        object InfoScreenType: ScreenTypes()
    }

    class TodoScreen(routerDependencies: RouterDependencies) : Screens(){
        private val router = TodoRouter(routerDependencies)

        override fun router(): Router {
            return router
        }
        override fun getScreenType(): ScreenType {
            return ScreenTypes.TodoScreenType
        }

    }
}