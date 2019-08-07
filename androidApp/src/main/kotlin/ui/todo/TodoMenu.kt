package org.linya.todo.multiplatform.ui.todo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import com.linya.utils.interfaces.RenderView
import com.linya.utils.mvi.Store
import com.linya.utils.ui.todo.TodoMenuRenderView
import com.linya.utils.ui.todo.TodoAddTaskStorage
import com.linya.utils.ui.todo.TodoMenuStorage
import org.linya.todo.multiplatform.R

class TodoMenu(context: Context) : FrameLayout(context), TodoMenuRenderView {
    private var storage: Store<TodoMenuStorage.TodoMenuWish, TodoMenuStorage.TodoMenuState>? = null
    private val menu: View

    init {
        LayoutInflater.from(context).inflate(R.layout.view_todo_menu, this, true)
        menu = findViewById(R.id.menu)

        setupClickListeners()
        setupStartAnimation()
    }

    private fun setupClickListeners() {
        val root = findViewById<View>(R.id.root)
        root.setOnClickListener {
            this.storage?.accept(TodoMenuStorage.TodoMenuWish.CloseMenu)
        }
    }

    private fun setupStartAnimation(){
        val onPreDrawListener = object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                menu.viewTreeObserver.removeOnPreDrawListener(this)

                menu.translationY = menu.height.toFloat()
                menu.animate().translationY(0f).setDuration(300).start()

                return true
            }
        }
        menu.viewTreeObserver.addOnPreDrawListener(onPreDrawListener)
    }

    override fun render(state: TodoMenuStorage.TodoMenuState) {}

    override fun setupPresenter(presenter: Store<TodoMenuStorage.TodoMenuWish, TodoMenuStorage.TodoMenuState>) {
        storage = presenter
    }

    override fun haveRemoveAnimation(): Boolean {
        return true
    }

    override fun removeFromParentViewAnimated(listener: RenderView.AnimationListener) {
        menu.animate().translationY(menu.height.toFloat()).setListener(
                object :AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        listener.animationEnded()
                    }
                }
        ).setDuration(300).start()
    }


}
