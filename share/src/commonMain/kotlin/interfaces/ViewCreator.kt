package com.linya.utils.interfaces

import com.linya.utils.mvi.StorageView

interface ViewCreator{
    fun createView(viewType: ViewType) : StorageView<out Any, out Any>?
}