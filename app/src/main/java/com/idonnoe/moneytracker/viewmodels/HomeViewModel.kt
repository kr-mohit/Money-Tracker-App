package com.idonnoe.moneytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idonnoe.moneytracker.data.Expense
import com.idonnoe.moneytracker.repository.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val expenseRepository: ExpenseRepository): ViewModel() {

    val listOfExpense = expenseRepository.listOfExpenses

    fun insertExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.insertExpense(expense)
        }
    }

    fun deleteAllExpenses() {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.deleteAllExpenses()
        }
    }

    fun getCategorizedList(category: String): List<Expense> {
        return listOfExpense.value?.let { list ->
            when(category) {
                "All" -> list
                "Other" -> list.filter { it.category != "Need" }.filter { it.category != "Want" }.filter { it.category != "Investment" }
                else -> list.filter { it.category == category }
            }
        } ?: emptyList()
    }
}