package com.example.todolist.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.todolist.R
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.model.TodoModel

class MainAdapter(
    private var list: List<TodoModel>,
    private var onItemClickCallback: OnItemClickCallback
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<TodoModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTodoBinding.bind(itemView)

        fun bindData(data: TodoModel) {
            binding.run {
                txtTitle.text = data.title
                txtDate.text = data.date
                val type = data.priority
                if (type.equals("Low")) {
                    binding.imgPriortty.setCardBackgroundColor(Color.GREEN)
                } else if (type.equals("Medium")) {
                    binding.imgPriortty.setCardBackgroundColor(Color.YELLOW)
                } else {
                    binding.imgPriortty.setCardBackgroundColor(Color.RED)
                }
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClick(data)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClick(todoModel: TodoModel)
    }

}