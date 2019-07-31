package org.konan.multiplatform.ui.login

import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import mvi.Storage
import ui.todo.TodoStorageView
import utils.ui.Screen
import utils.ui.login.TodoStorage

class TodoView//output?.userDidPressLogin(username = username.text.toString(), password = password.text.toString())
(context: Context) : FrameLayout(context), TodoStorageView {
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
            text = "Login"
        }
        addView(linearLayout)
    }

    override fun render(state: TodoStorage.TodoState) {
        val x = 0
    }

    override fun setupPresenter(storage: Storage<TodoStorage.TodoWish, TodoStorage.TodoState, out Any>) {
        this.storage = storage
        android.os.Handler().postDelayed(
            {
                this.storage?.accept(TodoStorage.TodoWish.Login)

            },
            2000L
        )
    }

    override fun getScreen(): Screen<out Any, out Any> {
        return Screen(this,Screen.ScreenType.Login)
    }
}
