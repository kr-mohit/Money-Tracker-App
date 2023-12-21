package com.idonnoe.moneytracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idonnoe.moneytracker.ExpenseAdapter
import com.idonnoe.moneytracker.R
import com.idonnoe.moneytracker.data.Expense
import com.idonnoe.moneytracker.databinding.FragmentViewExpenseBinding
import com.idonnoe.moneytracker.viewmodels.HomeViewModel
import com.idonnoe.moneytracker.viewmodels.HomeViewModelFactory

class ViewExpenseFragment : BaseFragment() {

    private lateinit var binding: FragmentViewExpenseBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_expense, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]

        setListeners()
        addDataToView()
    }

    private fun setListeners() {
        binding.ivGoBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnDeleteAll.setOnClickListener {
            homeViewModel.deleteAllExpenses()
        }

        binding.rgRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = requireActivity().findViewById<RadioButton>(checkedId)
            val selectedOption = radioButton.text.toString()

            addListToView(homeViewModel.getCategorizedList(selectedOption))
        }
    }

    private fun addDataToView() {
        homeViewModel.listOfExpense.observe(viewLifecycleOwner) {
            addListToView(it)
        }
    }

    private fun addListToView(list: List<Expense>) {
        if (list.isEmpty()) {
            binding.groupData.visibility = View.GONE
            binding.groupNoData.visibility = View.VISIBLE
        } else {
            binding.groupData.visibility = View.VISIBLE
            binding.groupNoData.visibility = View.GONE
            val adapter = ExpenseAdapter()
            adapter.submitList(list)
            binding.rvExpenses.adapter = adapter
            binding.rvExpenses.layoutManager = LinearLayoutManager(context)
        }
    }
}