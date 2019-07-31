package ui.root

import android.content.Context
import android.widget.FrameLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.produce
import mvi.Storage
import ribs.RenderView
import ribs.RibView
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