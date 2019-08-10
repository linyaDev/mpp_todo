package com.linya.utils.ui

import com.linya.utils.interfaces.*

class TodoRootRouter : RootRouter(){
    override fun getInitScreen(): Screen {
        return Screens.TodoScreen(this)
    }
}

