package com.example.todolist.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todolist.model.TodoModel
import com.example.todolist.repository.TodoRepository
import com.example.todolist.room.TodoDB

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository
    val allTodo: LiveData<List<TodoModel>>

    init {
        val todoDao = TodoDB.getInstance(application).todoDao()
        repository = TodoRepository(todoDao)
        allTodo = repository.allTodo
    }

}