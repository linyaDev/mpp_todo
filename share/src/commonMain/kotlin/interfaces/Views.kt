package com.linya.utils.interfaces

import com.linya.utils.mvi.Storage
import com.linya.utils.mvi.Store
import com.linya.utils.router.Router

// Screens
interface ScreenType
interface Screen{
    fun router(): Router
    fun getScreenType(): ScreenType
}

// Views
// type for view build
interface  ViewType

// view for mvi render
interface RenderView<Wish: Any,State : Any, News : Any>{
    fun render(state: State)
    fun setupPresenter(presenter: Store<Wish, State>)

    //optional for animation
    fun haveRemoveAnimation():Boolean{ return false }
    fun removeFromParentViewAnimated(listener : AnimationListener){}

    interface AnimationListener{
        fun animationEnded()
    }
}
