package com.idonnoe.moneytracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idonnoe.moneytracker.data.Expense
import java.text.SimpleDateFormat
import java.util.Locale

class ExpenseAdapter: ListAdapter<Expense, ExpenseAdapter.ExpenseViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expense_item_view, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvAmount: TextView = itemView.findViewById(R.id.tv_amount)
        private val tvDetails: TextView = itemView.findViewById(R.id.tv_details)
        private val tvCategory: TextView = itemView.findViewById(R.id.tv_category)

        private val tvDate: TextView = itemView.findViewById(R.id.tv_date)

        fun bind(item: Expense) {

            tvAmount.text = item.amount.toString()
            tvAmount.setTextColor(Color.WHITE)

            tvDetails.text = item.details
            tvDetails.setTextColor(Color.WHITE)

            tvCategory.text = item.category
            tvCategory.setTextColor(Color.WHITE)

            val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH)
            tvDate.text = dateFormat.format(item.date)
            tvDate.setTextColor(Color.WHITE)
        }
    }

    class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<Expense>() {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }

    }
}