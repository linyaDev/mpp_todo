package org.linya.todo.multiplatform.ui.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomappbar.BottomAppBar
import com.linya.utils.mvi.Store
import com.linya.utils.ui.todo.TodoMainStorage
import com.linya.utils.ui.todo.TodoMainRenderView
import org.linya.todo.multiplatform.R
import org.linya.todo.multiplatform.ui.todo.adapters.TasksAdapter

class TodoMainView(context: Context) : FrameLayout(context), TodoMainRenderView {
    private var mainStorage: Store<TodoMainStorage.TodoWish, TodoMainStorage.TodoState>? = null
    private val adapter = TasksAdapter()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_todo_main , this, true)

        val bar = findViewById<BottomAppBar>(R.id.bottom_app_bar)
        val fab = findViewById<View>(R.id.fab)

        val listView = findViewById<RecyclerView>(R.id.listView)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        listView.layoutManager = layoutManager
        listView.adapter = adapter

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
        adapter.items = state.todoList
        adapter.notifyDataSetChanged()
    }


    override fun setupPresenter(presenter: Store<TodoMainStorage.TodoWish, TodoMainStorage.TodoState>) {
        this.mainStorage = presenter
    }
}
