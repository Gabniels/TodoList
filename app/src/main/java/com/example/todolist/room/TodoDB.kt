package com.example.todolist.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.model.TodoModel

@Database(
    entities = [TodoModel::class],
    version = 1
)

abstract class TodoDB : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDB? = null

        fun getInstance(context: Context): TodoDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(TodoDB::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDB::class.java,
                    "todo.db"
                ).build()

                INSTANCE = instance
                return instance

            }
        }

//            instance ?: synchronized(this) {
//                instance ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    TodoDB::class.java,
//                    "Todo.DB"
//                ).build()
//            }
    }

}