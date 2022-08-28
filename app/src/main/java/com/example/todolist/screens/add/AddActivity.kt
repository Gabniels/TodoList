package com.example.todolist.screens.add

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.todolist.R
import com.example.todolist.databinding.ActivityAddBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    private val viewModel: AddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        addData()

    }

    private fun setupView() {
        binding.layoutAdd.apply {
            txtTitle.text = "Add a new Task"
            btnAdd.text = "ADD"

            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            btnDate.setOnClickListener {
                val datePickerDialog = DatePickerDialog(
                    this@AddActivity, { _, myear, mmonth, mdayOfMonth ->

                        val simpleDateFormat =
                            SimpleDateFormat("EEEE, d MMMM yy", Locale.getDefault())
                        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
                        val date = Date(myear, mmonth, mdayOfMonth)
                        val dayString = simpleDateFormat.format(date)
                        txtDate.text = dayString
                    },
                    year, month, day
                )
                datePickerDialog.show()
            }
        }
    }

    private fun addData() {
        binding.layoutAdd.apply {
            btnAdd.setOnClickListener {
                val title = edtAddTask.text.toString()
                val date = txtDate.text.toString()
                val description = edtAddDescription.text.toString()
                if (!title.isNullOrEmpty() && !date.isNullOrEmpty() && !description.isNullOrEmpty()) {
                    viewModel.addTodo(title, date, description)
                    Toast.makeText(
                        applicationContext,
                        "Data Berhasil Ditambahkan",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else Toast.makeText(
                    applicationContext,
                    "Form Tidak Boleh Kosong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}