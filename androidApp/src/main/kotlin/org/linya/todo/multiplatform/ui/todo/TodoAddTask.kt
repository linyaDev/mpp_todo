package org.linya.todo.multiplatform.ui.todo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import com.linya.utils.interfaces.RenderView
import com.linya.utils.mvi.Store
import com.linya.utils.ui.todo.TodoAddTaskRenderView
import com.linya.utils.ui.todo.TodoAddTaskStorage
import com.linya.utils.ui.todo.models.TodoModel
import org.linya.todo.multiplatform.R

class TodoAddTask(context: Context) : FrameLayout(context), TodoAddTaskRenderView {
    private var storage: Store<TodoAddTaskStorage.TodoAddWish, TodoAddTaskStorage.TodoAddState>? = null
    private val menu: View

    init {
        LayoutInflater.from(context).inflate(R.layout.view_todo_add, this, true)
        menu = findViewById(R.id.menu)

        setupClickListeners()
        setupStartAnimation()
    }

    private fun setupClickListeners() {
        val root = findViewById<View>(R.id.root)
        val submit = findViewById<Button>(R.id.submit)
        val taskTitle = findViewById<EditText>(R.id.taskTitle)
        val taskText = findViewById<EditText>(R.id.taskText)
        val subjects = findViewById<View>(R.id.subjects)

        taskText.visibility = View.GONE

        root.setOnClickListener {
            this.storage?.accept(TodoAddTaskStorage.TodoAddWish.CloseAddTaskMenu)
        }

        submit.setOnClickListener {
            this.storage?.accept(TodoAddTaskStorage.TodoAddWish.AddTask(TodoModel.TodoModelNote(taskTitle.text.toString(),taskText.text.toString())))
            taskTitle.text.clear()
            taskText.text.clear()
            this.storage?.accept(TodoAddTaskStorage.TodoAddWish.CloseAddTaskMenu)
        }

        subjects.setOnClickListener {
            taskText.visibility = View.VISIBLE
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

    override fun render(state: TodoAddTaskStorage.TodoAddState) {}

    override fun setupPresenter(presenter: Store<TodoAddTaskStorage.TodoAddWish, TodoAddTaskStorage.TodoAddState>) {
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
