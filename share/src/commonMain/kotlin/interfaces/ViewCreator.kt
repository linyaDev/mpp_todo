package com.linya.utils.interfaces


interface ViewCreator{
    fun createView(viewType: ViewType) : StorageView<out Any, out Any>?
}