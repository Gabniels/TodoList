package com.example.todolist.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.TodoModel
import com.example.todolist.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {

    fun deleteTodo(
        id: Int,
        title: String,
        date: String,
        description: String
    ) = viewModelScope.launch {
        repository.deleteTodo(TodoModel(id, title, date, description))
    }

}