package ui.root

import android.content.Context
import mvi.Storage
import ribs.RenderView
import ribs.ViewCreator
import ribs.ViewNavigator
import utils.ui.Screen

actual class RootView :  RenderView<Any,Any> {

    override fun render(state: Any) {}
    override fun setupPresenter(presenter: Storage<Any, Any, out Any>) {}
    override fun getScreen(): Screen<Any, Any> {
        return Screen(this,Screen.ScreenType.Root)
    }
}

actual class RootViewProvider actual constructor(private val dependencies: OSSpecificDependencies) {
    actual fun getView(): RootView = RootView()
}

interface ContextHolder{
    val context: Context
}

actual interface OSSpecificDependencies: ContextHolder, ViewCreator, ViewNavigator