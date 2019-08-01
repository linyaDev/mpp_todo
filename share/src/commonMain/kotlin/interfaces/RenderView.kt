package com.linya.utils.interfaces

import com.linya.utils.mvi.Storage
import com.linya.utils.mvi.StorageView

interface RenderView<Wish,State>{
    fun render(state: State)
    fun setupPresenter(presenter: Storage<Wish, State, out Any>)
    fun viewType(): StorageView<out Any,out Any>
}