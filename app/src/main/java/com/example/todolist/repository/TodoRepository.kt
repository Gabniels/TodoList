package com.example.todolist.repository

import androidx.lifecycle.LiveData
import com.example.todolist.database.dao.TodoDao
import com.example.todolist.model.TodoModel
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    fun getAllTodo(): LiveData<List<TodoModel>> {
        return todoDao.getTodoList()
    }

    suspend fun addTodo(todo: TodoModel) {
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: TodoModel) {
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: TodoModel) {
        todoDao.deleteTodo(todo)
    }

}