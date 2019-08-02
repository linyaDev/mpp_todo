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
        private var rootRouter: RootRouter? = null
    }

    private lateinit var rootView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (rootRouter == null)
            rootRouter = RootRouter()

        // save router in di
        rootView = FrameLayout(this)
        rootRouter?.activate(this)
        setContentView(rootView)
    }

    override fun onDestroy() {
        super.onDestroy()
        rootRouter?.deactivate()
    }

    override fun viewCreator(): ViewCreator {
        return this
    }

    override fun viewHolder(): ViewHolder {
        return this
    }

    override fun createView(viewType: ViewType): StorageView<out Any, out Any>? {
        if(viewType is TodoViewTypes){
            when(viewType){
                TodoViewTypes.TodoTable -> return StorageView(TodoView(this), viewType)
            }
        }

        return null
    }

    override fun addView(storageView: StorageView<out Any, out Any>) {
        val view = storageView.renderView
        if (view is View) {
            rootView.addView(view)
        }
    }

    override fun removeView(storageView: StorageView<out Any, out Any>) {
        val view = storageView.renderView
        if (view is View) {
            rootView.removeView(view)
        }
    }
}