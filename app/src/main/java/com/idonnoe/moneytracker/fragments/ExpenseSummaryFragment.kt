package com.idonnoe.moneytracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.idonnoe.moneytracker.R
import com.idonnoe.moneytracker.databinding.FragmentExpenseSummaryBinding
import com.idonnoe.moneytracker.viewmodels.HomeViewModel
import com.idonnoe.moneytracker.viewmodels.HomeViewModelFactory

class ExpenseSummaryFragment : BaseFragment() {

    private lateinit var binding: FragmentExpenseSummaryBinding
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expense_summary, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]

        setValuesInTable()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.ivGoBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setValuesInTable() {
        homeViewModel.listOfExpense.observe(viewLifecycleOwner) { list ->

            val needExpenseList = list.filter { it.category == "Need" }
            binding.tvNeedsExpense.text = needExpenseList.map { it.amount }.sum().toString()

            val wantExpenseList = list.filter { it.category == "Want" }
            binding.tvWantsExpense.text = wantExpenseList.map { it.amount }.sum().toString()

            val investmentExpenseList = list.filter { it.category == "Investment" }
            binding.tvInvestmentExpense.text = investmentExpenseList.map { it.amount }.sum().toString()

            val othersExpenseList = list.filter { it.category != "Need" }.filter { it.category != "Want" }.filter { it.category != "Investment" }
            binding.tvOthersExpense.text = othersExpenseList.map { it.amount }.sum().toString()

            binding.tvTotalExpense.text = activity?.getString(R.string.total_expense, list.map { it.amount }.sum().toString())

        }
    }
}