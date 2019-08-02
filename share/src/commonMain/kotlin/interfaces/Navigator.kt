package com.linya.utils.interfaces

import com.linya.utils.router.Router


interface Navigator{
    fun navigateTo(screen: Screen)
    fun newRootScreen(screen: Screen){}
    fun replaceScreen(screen: Screen){}
    fun backTo(screen: Screen){}
}