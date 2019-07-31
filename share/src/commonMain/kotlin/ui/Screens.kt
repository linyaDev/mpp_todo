package utils.ui

import mvi.RenderView


data class Screen<Wish,State>(val renderView: RenderView<Wish, State>,
                  val screenType: ScreenType){

    enum class ScreenType{
        Root, Login
    }
}

