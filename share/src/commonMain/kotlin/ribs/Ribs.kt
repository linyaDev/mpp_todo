package com.linya.utils.ribs

import com.linya.utils.interfaces.RouterDependencies
import com.linya.utils.mvi.Storage

abstract class Router(private val dependencies: RouterDependencies) {

    private var isAttached = false
    private val interactors = mutableListOf<Storage<out Any,out Any,out Any>>()

    fun addInteractor(interactor: Storage<out Any,out Any,out Any>){
        if (isAttached) {
            dependencies.viewHolder().addView(interactor.storageView)
        }
        interactors.add(interactor)
    }

    fun removeInteractor(interactor: Storage<out Any,out Any,out Any>){
        if (isAttached) {
            dependencies.viewHolder().removeView(interactor.storageView)
        }
        interactors.remove(interactor)
    }

    fun didAttach() {
        isAttached = true
        interactors.forEach {
            dependencies.viewHolder().addView(it.storageView)
        }
    }

    fun didDetach() {
        isAttached = false
        interactors.forEach {
            dependencies.viewHolder().removeView(it.storageView)
        }
    }
}