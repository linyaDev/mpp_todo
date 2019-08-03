package org.linya.todo.multiplatform.ui.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.linya.utils.mvi.Storage
import com.linya.utils.mvi.Store
import com.linya.utils.ui.todo.TodoStorage
import com.linya.utils.ui.todo.TodoStorageView
import com.linya.utils.ui.todo.TodoViewTypes
import org.linya.todo.multiplatform.R

class TodoView(context: Context) : FrameLayout(context), TodoStorageView {
    private var storage: Store<TodoStorage.TodoWish, TodoStorage.TodoState>? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_todo , this, true)

        val bar = findViewById<BottomAppBar>(R.id.bottom_app_bar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        bar.setNavigationOnClickListener {
            this.storage?.accept(TodoStorage.TodoWish.ShowMenu)
        }

        MenuInflater(context).inflate(R.menu.menu, bar.menu)
        bar.menu.findItem(R.id.bookmark_menu).setOnMenuItemClickListener {
            true
        }
    }

    override fun render(state: TodoStorage.TodoState) {
        val x = 0
    }


    override fun setupPresenter(presenter: Store<TodoStorage.TodoWish, TodoStorage.TodoState>) {
        this.storage = presenter
        /*
        android.os.Handler().postDelayed(
                {
                    this.storage?.accept(TodoStorage.TodoWish.AddTodo)

                },
                2000L
        )*/
    }
}
