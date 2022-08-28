package com.example.todolist.repository

import androidx.lifecycle.LiveData
import com.example.todolist.model.TodoModel
import com.example.todolist.room.TodoDao

class TodoRepository(private val todoDao: TodoDao) {
    val allTodo: LiveData<List<TodoModel>> = todoDao.getTodoList()

    suspend fun addTodo(todoModel: TodoModel) {
        todoDao.addTodo(todoModel)
    }

    suspend fun deleteTodo(todoModel: TodoModel) {
        todoDao.deleteTodo(todoModel)
    }
}