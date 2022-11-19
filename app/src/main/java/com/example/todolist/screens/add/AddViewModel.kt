package com.example.todolist.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.TodoModel
import com.example.todolist.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {

    fun addTodo(
        title: String,
        date: String,
        description: String,
        priority: String
    ) {
        viewModelScope.launch {
            repository.addTodo(TodoModel(null, title, date, description, priority))
        }
    }

    fun updateTodo(
        id: Int,
        title: String,
        date: String,
        description: String,
        priority: String
    ) {
        viewModelScope.launch {
            repository.updateTodo(TodoModel(id, title, date, description, priority))
        }
    }

}