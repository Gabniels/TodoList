package com.example.todolist.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.model.TodoModel
import com.example.todolist.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TodoRepository) :
    ViewModel() {

    val getAllTodo: LiveData<List<TodoModel>> get() = repository.getAllTodo()


}