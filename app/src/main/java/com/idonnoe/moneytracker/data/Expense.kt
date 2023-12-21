package com.idonnoe.moneytracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "expense")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val amount: Float,
    val details: String,
    val category: String,
    val date: Date
)
