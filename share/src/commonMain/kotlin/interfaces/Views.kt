package com.linya.utils.interfaces

import com.linya.utils.mvi.Storage
import com.linya.utils.ribs.Router

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
interface RenderView<Wish,State>{
    fun render(state: State)
    fun setupPresenter(presenter: Storage<Wish, State, out Any>)
}

// view for storage
data class StorageView<Wish,State>(val renderView: RenderView<Wish, State>, val viewType: ViewType)
