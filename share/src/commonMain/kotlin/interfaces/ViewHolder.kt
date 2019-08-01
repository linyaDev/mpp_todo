package com.linya.utils.interfaces

import com.linya.utils.mvi.StorageView

interface ViewHolder{
    fun addView(storageView: StorageView<out Any, out Any>)
    fun removeView(storageView: StorageView<out Any, out Any>)
}