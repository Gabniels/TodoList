package com.example.todolist.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.model.TodoModel

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(todoModel: TodoModel)

    @Update
    suspend fun updateTodo(note: TodoModel)

    @Query("SELECT * FROM todo_tb")
    fun getTodoList(): LiveData<List<TodoModel>>

    @Delete
    suspend fun deleteTodo(todoModel: TodoModel)

}