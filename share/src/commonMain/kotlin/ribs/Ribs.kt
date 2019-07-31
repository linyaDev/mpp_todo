package ribs

import mvi.Storage
import ui.root.OSSpecificDependencies
import utils.ui.Screen
import kotlin.reflect.KClass


interface RenderView<Wish,State>{
    fun render(state: State)
    fun setupPresenter(presenter: Storage<Wish, State, out Any>)
    fun getScreen(): Screen<out Any,out Any>
}

interface ViewNavigator{
    fun addView(screen: Screen<out Any, out Any>)
    fun removeView(screen: Screen<out Any, out Any>)
    fun replaceView(previewScreen: Screen<out Any, out Any>,nextScreen: Screen<out Any, out Any>)
}

interface ViewCreator{
    fun createView(screenType: Screen.ScreenType) : Screen<out Any, out Any>
}

abstract class Router(private val dependencies: OSSpecificDependencies) {

    protected var child:Router? = null
    private var isAttached = false

    abstract fun getScreen(): Screen.ScreenType

    private val interactors = mutableListOf<Storage<out Any,out Any,out Any>>()

    fun addInteractor(interactor: Storage<out Any,out Any,out Any>){
        if (isAttached) {
            dependencies.addView(interactor.screen)
        }
        interactors.add(interactor)
    }

    fun removeInteractor(interactor: Storage<out Any,out Any,out Any>){
        if (isAttached) {
            dependencies.removeView(interactor.screen)
        }
        interactors.remove(interactor)
    }

    internal open fun didAttach() {
        isAttached = true
        interactors.forEach {
            dependencies.addView(it.screen)
        }
    }

    private fun didDetach() {
        isAttached = false
        interactors.forEach {
            dependencies.removeView(it.screen)
        }
    }

    open fun activate() {
        child?.activate()
    }

    fun deactivate() {
        child?.deactivate()
    }

    internal fun attach(router: Router) {
        if (child!=null){
            child?.didDetach()
            child = null
        }

        child = router
        router.didAttach()
    }

    internal fun detach(router: Router) {
        child?.didDetach()
        child = null
        router.didDetach()
    }
}