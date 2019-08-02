package org.linya.todo.multiplatform

import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.linya.utils.interfaces.*
import com.linya.utils.ui.RootRouter
import com.linya.utils.ui.todo.TodoViewTypes
import org.linya.todo.multiplatform.ui.todo.TodoView

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        println("Application init")
    }
}

class MainActivity : AppCompatActivity(), OSDependencies, ViewCreator, ViewHolder {

    companion object {
        private var rootRouter: RootRouter = RootRouter()
    }

    private lateinit var rootView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rootView = FrameLayout(this)
        setContentView(rootView)

        rootRouter.activate(this)
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

    override fun createView(viewType: ViewType): RenderView<out Any,out  Any,out Any>? {
        if(viewType is TodoViewTypes){
            when(viewType){
                TodoViewTypes.TodoTable -> return TodoView(this)
            }
        }

        return null
    }

    override fun addView(renderView: RenderView<out Any, out Any, out Any>) {
        if (renderView is View) {
            rootView.addView(renderView)
        }
    }

    override fun removeView(renderView: RenderView<out Any, out Any, out Any>) {
        if (renderView is View) {
            rootView.removeView(renderView)
        }
    }
}