package utils.ui

import ribs.RenderView


data class Screen<Wish,State>(val renderView: RenderView<Wish, State>,
                  val screenType: ScreenType){

    enum class ScreenType{
        Root, Login
    }
}

