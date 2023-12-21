package com.idonnoe.moneytracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {

    @Query("SELECT * from expense order by Date Desc")
    fun getExpenses(): LiveData<List<Expense>>

    @Insert
    fun insertExpense(expense: Expense)

    @Query("DELETE from expense")
    fun deleteAllExpenses()
}