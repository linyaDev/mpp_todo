package com.linya.utils.interfaces

interface RouterDependencies{
    fun viewCreator(): ViewCreator
    fun viewHolder(): ViewHolder
    fun navigator(): Navigator
}