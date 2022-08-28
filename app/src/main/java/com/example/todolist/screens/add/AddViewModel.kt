package com.example.todolist.screens.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.TodoModel
import com.example.todolist.repository.TodoRepository
import com.example.todolist.room.TodoDB
import com.example.todolist.room.TodoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository

    init {
        val todoDao = TodoDB.getInstance(application).todoDao()
        repository = TodoRepository(todoDao)
    }

    fun addTodo(
        title: String,
        date: String,
        description: String
    ) = viewModelScope.launch {
        repository.addTodo(TodoModel(null, title, date, description))
    }

}