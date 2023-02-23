package com.example.todolist.screens.add

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.databinding.ActivityAddBinding
import com.example.todolist.screens.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityAddBinding

    private val viewModel: AddViewModel by viewModels()

    lateinit var resultType: String
    private var resultDate: String? = null
    private lateinit var spinnerObject: Spinner

    private var noteId: Int = 0
    private var title: String? = null
    private var date: String? = null
    private var type: String? = null
    private var desc: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupView()
        addData()
        editData()

    }

    private fun setupView() {
        noteId = intent.getIntExtra("id", 0)
        title = intent.getStringExtra("title")
        date = intent.getStringExtra("date")
        type = intent.getStringExtra("type")
        desc = intent.getStringExtra("desc")

        if (noteId != 0) {
            binding.layoutAdd.txtTitle.text = "Edit Task"
            binding.layoutAdd.btnEdit.visibility = VISIBLE
            binding.layoutAdd.btnAdd.visibility = GONE

            var positionSpinner = 0

            if (this.type.equals("Low")) {
                positionSpinner = 0
            } else if (this.type.equals("Medium")) {
                positionSpinner = 1
            } else if (this.type.equals("High")) {
                positionSpinner = 2
            }

            spinnerObject.setSelection(positionSpinner)

        }

        binding.layoutAdd.apply {
            binding.layoutAdd.edtAddTask.setText(title)
            binding.layoutAdd.txtDate.text = date
            binding.layoutAdd.edtAddDescription.setText(desc)

            val cal = Calendar.getInstance()
            val date = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                resultDate = sdf.format(cal.time)
                val sdfConvert = SimpleDateFormat("E, dd-MMMM-yyyy", Locale.getDefault())
                binding.layoutAdd.txtDate.text = sdfConvert.format(cal.time)
            }

            btnCancel.setOnClickListener {
                finish()
            }

            btnDate.setOnClickListener {
                DatePickerDialog(
                    this@AddActivity, date,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).apply {
                    show()
                }
            }
        }
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.type,
            android.R.layout.simple_spinner_dropdown_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerObject = binding.layoutAdd.spinner
        spinnerObject.adapter = adapter
        spinnerObject.onItemSelectedListener = this

    }

    private fun editData() {
        binding.layoutAdd.apply {
            btnEdit.setOnClickListener {
                val title = edtAddTask.text.toString()
                val date = txtDate.text.toString()
                val type = resultType
                val desc = edtAddDescription.text.toString()

                if (!title.isNullOrEmpty() && !date.isNullOrEmpty() && !desc.isNullOrEmpty()) {
                    viewModel.updateTodo(
                        noteId, title, date, desc, type
                    )
                    startActivity(Intent(this@AddActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun addData() {
        binding.layoutAdd.apply {
            btnAdd.setOnClickListener {
                val title = edtAddTask.text.toString()
                val date = txtDate.text.toString()
                val type = resultType
                val description = edtAddDescription.text.toString()
                if (!title.isNullOrEmpty() && !date.isNullOrEmpty() && !description.isNullOrEmpty()) {
                    viewModel.addTodo(title, date, description, type)
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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        resultType = p0?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}