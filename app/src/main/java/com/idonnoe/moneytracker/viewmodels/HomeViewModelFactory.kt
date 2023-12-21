package com.idonnoe.moneytracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idonnoe.moneytracker.repository.ExpenseRepository

class HomeViewModelFactory(private val expenseRepository: ExpenseRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(expenseRepository) as T
    }
}