package com.linya.utils.interfaces

interface ViewHolder{
    fun addView(renderView: RenderView<out Any, out Any,out Any>)
    fun removeView(renderView: RenderView<out Any, out Any,out Any>)
}