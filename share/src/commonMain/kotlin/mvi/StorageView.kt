package com.linya.utils.mvi

import com.linya.utils.interfaces.RenderView
import com.linya.utils.interfaces.ViewType

data class StorageView<Wish,State>(val renderView: RenderView<Wish, State>, val viewType: ViewType)