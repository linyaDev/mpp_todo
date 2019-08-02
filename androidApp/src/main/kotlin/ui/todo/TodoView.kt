package org.linya.todo.multiplatform.ui.todo

import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.linya.utils.mvi.Storage
import com.linya.utils.ui.todo.TodoStorage
import com.linya.utils.ui.todo.TodoStorageView
import com.linya.utils.ui.todo.TodoViewTypes

class TodoView(context: Context) : FrameLayout(context), TodoStorageView {
    var storage: Storage<TodoStorage.TodoWish,TodoStorage.TodoState,out Any>? = null
    val username: EditText
    val password: EditText

    init {
        password = EditText(context)
        password.hint = "Password"
        username = EditText(context)
        username.hint = "Username"
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.addView(username)
        linearLayout.addView(password)
        val button = Button(context)
        linearLayout.addView(button)
        button.apply {
            setOnClickListener {
                //output?.userDidPressLogin(username = username.text.toString(), password = password.text.toString())
            }
            text = "TodoTable"
        }
        addView(linearLayout)
    }

    override fun render(state: TodoStorage.TodoState) {
        val x = 0
    }

    override fun setupPresenter(presenter: Storage<TodoStorage.TodoWish, TodoStorage.TodoState, out Any>) {
        this.storage = storage
        android.os.Handler().postDelayed(
            {
                this.storage?.accept(TodoStorage.TodoWish.AddTodo)

            },
            2000L
        )
    }
}
