package com.example.todolist.screens.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.todolist.R
import com.example.todolist.databinding.ActivityDetailBinding
import com.example.todolist.model.TodoModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()
    var todoData: TodoModel? = null

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getdata()
        setupView()
    }

    private fun getdata() {
        todoData = intent.getParcelableExtra("TODOMODEL")
    }

    private fun setupView() {
        binding.apply {
            txtTitle.text = todoData?.title
            txtDescription.text = todoData?.description
            btnDelete.setOnClickListener {
                viewModel.deleteTodo(
                    todoData?.id!!,
                    todoData?.title!!,
                    todoData?.date!!,
                    todoData?.description!!
                )
                Toast.makeText(
                    applicationContext,
                    "Data Berhasil Dihapus",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

}