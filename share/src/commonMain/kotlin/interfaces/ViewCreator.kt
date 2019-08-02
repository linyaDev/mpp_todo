package com.linya.utils.interfaces


interface ViewCreator{
    fun createView(viewType: ViewType) : RenderView<out Any, out Any>?
}