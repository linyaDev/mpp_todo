package org.linya.todo.multiplatform.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.linya.utils.interfaces.*
import com.linya.utils.ui.RootRouter
import com.linya.utils.ui.Screens
import com.linya.utils.ui.TodoRootRouter
import com.linya.utils.ui.todo.TodoViewTypes
import org.linya.todo.multiplatform.ui.todo.TodoAddTask
import org.linya.todo.multiplatform.ui.todo.TodoTasksMenu
import org.linya.todo.multiplatform.ui.todo.TodoMainView


class MainActivity : AppCompatActivity(), OSDependencies, ViewCreator, ViewHolder {

    companion object {
        private var rootRouter: RootRouter = TodoRootRouter()
    }

    private lateinit var rootView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rootView = FrameLayout(this)
        setContentView(rootView)

        actionBar?.hide()
        supportActionBar?.hide()
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
            return when(viewType){
                TodoViewTypes.TodoTable ->  TodoMainView(this)
                TodoViewTypes.TodoMenu -> TodoTasksMenu(this)
                TodoViewTypes.TodoAddTask -> TodoAddTask(this)
                else -> null
            }
        }

        return null
    }

    override fun addView(renderView: RenderView<out Any, out Any, out Any>) {
        if (renderView is View) {
            rootView.addView(renderView)
        }
    }

    override fun removeAnimated(renderView: RenderView<out Any, out Any, out Any>) {
        if (renderView is View) {
            renderView.removeFromParentViewAnimated(object : RenderView.AnimationListener {
                override fun animationEnded() {
                    rootView.removeView(renderView)
                }
            })
        }
    }

    override fun removeView(renderView: RenderView<out Any, out Any, out Any>) {
        if (renderView is View) {
            rootView.removeView(renderView)
        }

    }
}