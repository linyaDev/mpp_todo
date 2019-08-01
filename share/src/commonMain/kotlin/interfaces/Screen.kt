package com.linya.utils.interfaces

import com.linya.utils.ribs.Router

interface Screen{
    fun router(): Router
    fun getScreenType(): ScreenType
}