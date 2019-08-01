package com.linya.utils.ui

import com.linya.utils.interfaces.Builder
import com.linya.utils.interfaces.RouterDependencies
import com.linya.utils.interfaces.Screen
import com.linya.utils.interfaces.ScreenType
import com.linya.utils.ribs.Router
import com.linya.utils.ui.todo.TodoRouter

sealed class Screens: Screen{

    sealed class ScreenTypes: ScreenType{
        object TodoScreenType: ScreenTypes()
        object InfoScreenType: ScreenTypes()
    }

    data class TodoScreen(val routerDependencies: RouterDependencies) : Screens(){

        private val builder = TodoBuilder()
        private val router = builder.build(routerDependencies)

        override fun router(): Router {
            return router
        }
        override fun getScreenType(): ScreenType {
            return ScreenTypes.TodoScreenType
        }

        inner class TodoBuilder : Builder() {
            override fun build(dependencies: RouterDependencies): TodoRouter {
                return TodoRouter(dependencies)
            }
        }
    }
}