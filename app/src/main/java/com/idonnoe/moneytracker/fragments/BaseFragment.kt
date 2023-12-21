package com.idonnoe.moneytracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.idonnoe.moneytracker.HomeApplication
import com.idonnoe.moneytracker.R
import com.idonnoe.moneytracker.repository.ExpenseRepository

open class BaseFragment: Fragment() {

    open lateinit var repository: ExpenseRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = (activity?.applicationContext as HomeApplication).repository

    }

    fun gotoFragment(nextFragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment_container, nextFragment)
            addToBackStack(null)
            commit()
        }
    }

    fun onBackPressed() {
        requireActivity().onBackPressed()
    }
}