package com.idonnoe.moneytracker.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.idonnoe.moneytracker.R
import com.idonnoe.moneytracker.data.Expense
import com.idonnoe.moneytracker.databinding.FragmentHomeBinding
import com.idonnoe.moneytracker.viewmodels.HomeViewModel
import com.idonnoe.moneytracker.viewmodels.HomeViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomeFragment: BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(requireActivity(), HomeViewModelFactory(repository))[HomeViewModel::class.java]

        setListeners()
    }

    private fun setListeners() {

        binding.tvSelectDate.setOnClickListener {
            val mCurrentDate: Calendar = Calendar.getInstance()
            var mYear = mCurrentDate.get(Calendar.YEAR)
            var mMonth = mCurrentDate.get(Calendar.MONTH)
            var mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH)

            val mDatePicker = DatePickerDialog(requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val myCalendar: Calendar = Calendar.getInstance()
                    myCalendar.set(Calendar.YEAR, selectedYear)
                    myCalendar.set(Calendar.MONTH, selectedMonth)
                    myCalendar.set(Calendar.DAY_OF_MONTH, selectedDay)
                    val myFormat = "MM/dd/yy"
                    val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
                    binding.etExpenseD.setText(sdf.format(myCalendar.time))
                    mDay = selectedDay
                    mMonth = selectedMonth
                    mYear = selectedYear
                }, mYear, mMonth, mDay
            )
            mDatePicker.show()
        }

        binding.btnAddExpense.setOnClickListener {
            addExpense()
        }

        binding.btnViewExpense.setOnClickListener {
            gotoFragment(ViewExpenseFragment())
        }

        binding.btnViewSummary.setOnClickListener {
            gotoFragment(ExpenseSummaryFragment())
        }
    }

    private fun addExpense() {
        if (binding.etExpenseAmount.text.isBlank()) {
            Toast.makeText(context, "Please Enter Amount", Toast.LENGTH_SHORT).show()
        } else if (binding.etExpenseDetails.text.isBlank()) {
            Toast.makeText(context, "Please Enter Details", Toast.LENGTH_SHORT).show()
        } else if (binding.etExpenseCategory.text.isBlank()) {
            Toast.makeText(context, "Please Enter Category", Toast.LENGTH_SHORT).show()
        } else {
            homeViewModel.insertExpense(
                Expense(
                    0,
                    binding.etExpenseAmount.text.toString().toFloat(),
                    binding.etExpenseDetails.text.toString(),
                    binding.etExpenseCategory.text.toString(),
                    Date(binding.etExpenseD.text.toString())
                )
            )
            binding.etExpenseAmount.text.clear()
            binding.etExpenseDetails.text.clear()
            binding.etExpenseCategory.text.clear()
            binding.etExpenseD.text.clear()
            Toast.makeText(context, "Expense Added", Toast.LENGTH_SHORT).show()
        }
    }
}