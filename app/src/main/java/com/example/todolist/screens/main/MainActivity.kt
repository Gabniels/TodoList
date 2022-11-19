package com.example.todolist.screens.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.TodoModel
import com.example.todolist.screens.add.AddActivity
import com.example.todolist.screens.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickCallback {

    private lateinit var binding: ActivityMainBinding

        private val viewModel: MainViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListener()
        getData()
        setupView()
    }

    private fun setOnClickListener() {
        binding.btnFloat.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    private fun getData() {
        viewModel.getAllTodo.observe(this) {
            Log.e("ALLDATA", "getData: $it")
            if (!it.isNullOrEmpty()) {
                binding.rvListTodo.isVisible = true
                binding.layoutEmpty.isVisible = false
                mainAdapter.setData(it)
            } else {
                binding.rvListTodo.isVisible = false
                binding.layoutEmpty.isVisible = true
            }


        }
    }

    private fun setupView() {
        mainAdapter = MainAdapter(ArrayList(), this)
        binding.rvListTodo.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mainAdapter
        }
    }

    override fun onItemClick(todoModel: TodoModel) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .putExtra("TODOMODEL", todoModel)
        )
    }

}