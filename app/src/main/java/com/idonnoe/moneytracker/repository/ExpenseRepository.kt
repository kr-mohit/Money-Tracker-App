package com.idonnoe.moneytracker.repository

import com.idonnoe.moneytracker.data.Expense
import com.idonnoe.moneytracker.data.ExpenseDao

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val listOfExpenses = expenseDao.getExpenses()

    fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    fun deleteAllExpenses() {
        expenseDao.deleteAllExpenses()
    }
}