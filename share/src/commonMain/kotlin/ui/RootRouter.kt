package com.linya.utils.ui

import com.linya.utils.interfaces.*

class RootRouter : Navigator, RouterDependencies {
    private val routersList = mutableListOf<Screen>()
    private lateinit var dependencies: OSDependencies

    override fun navigateTo(screen:Screen){
        val router = screen.router()
        router.didAttach()

        if (routersList.isNotEmpty())
            routersList.last().router().didDetach()

        routersList.add(screen)
    }

     fun activate(dependencies: OSDependencies) {
        this.dependencies = dependencies

        if (routersList.isEmpty()){
            val screen = Screens.TodoScreen(this)
            navigateTo(screen)
        }else{
            routersList.last().router().didAttach()
        }
    }

    fun deactivate() {
        routersList.last().router().didDetach()
    }

    override fun viewCreator(): ViewCreator {
        return dependencies.viewCreator()
    }

    override fun viewHolder(): ViewHolder {
        return dependencies.viewHolder()
    }

    override fun navigator(): Navigator {
       return this
    }
}

