package ui.root

import mvi.Storage
import ribs.*
import platform.UIKit.UIViewController
import platform.UIKit.addSubview
import platform.UIKit.removeFromSuperview
import platform.UIKit.setFrame
import utils.ui.Screen

actual class RootView : RenderView<Any,Any> {
    override fun render(state: Any) {
    }

    override fun setupPresenter(presenter: Storage<Any, Any, out Any>) {
    }

    override fun getScreen(): Screen<out Any, out Any> {
        return Screen(this,Screen.ScreenType.Root)
    }
}

actual class RootViewProvider actual constructor(private val dependencies: OSDependencies) {
    actual fun getView() = RootView()
}

actual interface OSSpecificDependencies: ViewCreator,ViewHolder