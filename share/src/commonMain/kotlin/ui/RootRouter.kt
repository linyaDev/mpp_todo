package ui.root

import mvi.ViewCreator
import mvi.ViewHolder
import ribs.Router
import ui.todo.LoginBuilder


class RootBuilder(private val dependencies: OSDependencies) {

    fun build(): RootRouter {
         return RootRouter(dependencies)
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
    fun navigator(): Navigator
}

class RootRouter(private val dependencies: OSDependencies) : Navigator{
    private val routersList = mutableListOf<Router>()

    private fun moveTo(router: Router){
        routersList.add(router)
        router.didAttach()
    }

     fun activate() {

        if (routersList.isEmpty()){
            val router = LoginBuilder().build(dependencies)
            moveTo(router)
        }else{
            routersList.last().didAttach()
        }
    }

    fun deactivate() {
        routersList.last().didDetach()
    }

}

