package com.linya.utils.interfaces

interface ViewHolder{
    fun addView(storageView: StorageView<out Any, out Any>)
    fun removeView(storageView: StorageView<out Any, out Any>)
}