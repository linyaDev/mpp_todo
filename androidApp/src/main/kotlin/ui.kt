package org.konan.multiplatform

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import mvi.ViewCreator
import mvi.ViewHolder
import org.konan.multiplatform.ui.login.TodoView
import ui.root.*
import utils.ui.Screen

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        println("Application init")
    }
}

class MainActivity : AppCompatActivity(), OSDependencies , ViewCreator, ViewHolder , Navigator {

    private lateinit var rootRouter: RootRouter
    private lateinit var rootView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rootRouter = RootBuilder(this).build()

        // save router in di
        rootView = FrameLayout(this)
        rootRouter.activate()
        setContentView(rootView)
    }

    override fun onDestroy() {
        super.onDestroy()
        rootRouter.deactivate()
    }

    override fun viewCreator(): ViewCreator {
        return this
    }

    override fun viewHolder(): ViewHolder {
        return this
    }

    override fun navigator(): Navigator {
        return rootRouter
    }

    override fun createView(screenType: Screen.ScreenType): Screen<out Any, out Any> {
        return Screen(TodoView(this),screenType)
    }

    override fun addView(screen: Screen<out Any, out Any>) {
        val view = screen.renderView
        if (view is View) {
            rootView.addView(view)
        }
    }

    override fun removeView(screen: Screen<out Any, out Any>) {

    }
}