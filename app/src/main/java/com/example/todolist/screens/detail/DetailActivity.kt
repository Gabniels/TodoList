package com.example.todolist.screens.detail

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.todolist.databinding.ActivityDetailBinding
import com.example.todolist.model.TodoModel
import com.example.todolist.screens.add.AddActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            txtDate.text = todoData?.date
            val type = todoData?.priority
            if (type.equals("Low")) {
                binding.imgPriortty.setCardBackgroundColor(Color.GREEN)
            } else if (type.equals("Medium")) {
                binding.imgPriortty.setCardBackgroundColor(Color.YELLOW)
            } else {
                binding.imgPriortty.setCardBackgroundColor(Color.RED)
            }
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

            binding.btnUpdate.setOnClickListener {

                startActivity(
                    Intent(applicationContext, AddActivity::class.java)
                        .putExtra("id", todoData?.id!!)
                        .putExtra("title", todoData?.title!!)
                        .putExtra("date", todoData?.date!!)
                        .putExtra("type", todoData?.priority)
                        .putExtra("desc", todoData?.description)
                )
            }

        }
    }

}