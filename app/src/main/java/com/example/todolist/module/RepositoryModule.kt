package com.example.todolist.module

import com.example.todolist.database.dao.TodoDao
import com.example.todolist.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTodoRepository(
        todoDao: TodoDao
    ) = TodoRepository(
        todoDao = todoDao
    )

}