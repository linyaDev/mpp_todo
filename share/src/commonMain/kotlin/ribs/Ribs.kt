package ribs

import mvi.Storage
import ui.root.OSDependencies
import utils.ui.Screen

abstract class Router(private val dependencies: OSDependencies) {

    private var isAttached = false
    private val interactors = mutableListOf<Storage<out Any,out Any,out Any>>()

    fun addInteractor(interactor: Storage<out Any,out Any,out Any>){
        if (isAttached) {
            dependencies.viewHolder().addView(interactor.screen)
        }
        interactors.add(interactor)
    }

    fun removeInteractor(interactor: Storage<out Any,out Any,out Any>){
        if (isAttached) {
            dependencies.viewHolder().removeView(interactor.screen)
        }
        interactors.remove(interactor)
    }

    fun didAttach() {
        isAttached = true
        interactors.forEach {
            dependencies.viewHolder().addView(it.screen)
        }
    }

    fun didDetach() {
        isAttached = false
        interactors.forEach {
            dependencies.viewHolder().removeView(it.screen)
        }
    }
}