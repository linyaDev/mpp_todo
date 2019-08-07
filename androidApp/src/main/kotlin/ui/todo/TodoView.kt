package org.linya.todo.multiplatform.ui.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.widget.FrameLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.linya.utils.mvi.Store
import com.linya.utils.ui.todo.TodoMainStorage
import com.linya.utils.ui.todo.TodoMainRenderView
import org.linya.todo.multiplatform.R

class TodoView(context: Context) : FrameLayout(context), TodoMainRenderView {
    private var mainStorage: Store<TodoMainStorage.TodoWish, TodoMainStorage.TodoState>? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_todo , this, true)

        val bar = findViewById<BottomAppBar>(R.id.bottom_app_bar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener{
            this.mainStorage?.accept(TodoMainStorage.TodoWish.ShowAddTask)
        }

        bar.setNavigationOnClickListener {
            this.mainStorage?.accept(TodoMainStorage.TodoWish.ShowMenu)
        }

        MenuInflater(context).inflate(R.menu.menu, bar.menu)
        bar.menu.findItem(R.id.bookmark_menu).setOnMenuItemClickListener {
            this.mainStorage?.accept(TodoMainStorage.TodoWish.ShowFilter)
            true
        }
    }

    override fun render(state: TodoMainStorage.TodoState) {
        val x = 0
    }


    override fun setupPresenter(presenter: Store<TodoMainStorage.TodoWish, TodoMainStorage.TodoState>) {
        this.mainStorage = presenter
    }
}
