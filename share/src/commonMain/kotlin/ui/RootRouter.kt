package ui.root

import mvi.ViewCreator
import mvi.ViewHolder
import ribs.Router
import ui.todo.LoginBuilder


class RootBuilder {

    fun build(): RootRouter {
         return RootRouter()
    }
}

interface Navigator{
    fun navigateTo(router: Router){}
    fun newRootScreen(router: Router){}
    fun replaceScreen(router: Router){}
    fun backTo(router: Router){}
}

interface OSDependencies{
    fun viewCreator(): ViewCreator
    fun viewHolder(): ViewHolder
}

interface RouterDependencies{
    fun viewCreator(): ViewCreator
    fun viewHolder(): ViewHolder
    fun navigator(): Navigator
}

class RootRouter : Navigator , RouterDependencies{
    private val routersList = mutableListOf<Router>()
    private lateinit var dependencies: OSDependencies
    private fun moveTo(router: Router){
        routersList.add(router)
        router.didAttach()
    }

     fun activate(dependencies: OSDependencies) {
        this.dependencies = dependencies

        if (routersList.isEmpty()){
            val router = LoginBuilder().build(this)
            moveTo(router)
        }else{
            routersList.last().didAttach()
        }
    }

    fun deactivate() {
        routersList.last().didDetach()
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

