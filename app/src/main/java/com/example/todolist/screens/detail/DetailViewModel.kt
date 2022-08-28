package com.example.todolist.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.TodoModel
import com.example.todolist.repository.TodoRepository
import com.example.todolist.room.TodoDB
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository

    init {
        val todoDao = TodoDB.getInstance(application).todoDao()
        repository = TodoRepository(todoDao)
    }

    fun deleteTodo(
        id: Int,
        title: String,
        date: String,
        description: String
    ) = viewModelScope.launch {
        repository.deleteTodo(TodoModel(id, title, date, description))
    }

}