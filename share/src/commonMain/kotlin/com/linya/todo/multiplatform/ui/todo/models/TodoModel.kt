package com.linya.utils.ui.todo.models

sealed class TodoModel{
    data class TodoModelHeader(val title: String= "Title"): TodoModel()
    data class TodoModelNote(val title: String= "", val text: String= ""): TodoModel()
}