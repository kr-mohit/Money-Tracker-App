package com.idonnoe.moneytracker

import android.app.Application
import com.idonnoe.moneytracker.data.ExpenseDatabase
import com.idonnoe.moneytracker.repository.ExpenseRepository

class HomeApplication: Application() {

    lateinit var repository: ExpenseRepository

    override fun onCreate() {
        super.onCreate()

        initializeRepository()
    }

    private fun initializeRepository() {
        val database = ExpenseDatabase.getDatabase(applicationContext)
        val dao = database.expenseDao()
        repository = ExpenseRepository(dao)
    }
}