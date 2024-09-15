package com.shaik.pennypilot.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codewithfk.expensetracker.android.R
import com.shaik.pennypilot.data.ExpenseDataBase
import com.shaik.pennypilot.data.dao.ExpenseDao
import com.shaik.pennypilot.data.model.ExpenseEntity

class HomeViewModel(dao : ExpenseDao) : ViewModel() {
    val expenses = dao.getAllExpenses()

    fun getBalance(list:List<ExpenseEntity>): String {
        var total = 0.0
        list.forEach {
            if(it.type == "income"){
                total += it.amount
            }else{
                total -= it.amount
            }
        }
        return "₹ ${total}"


    }

    fun getTotalExpense(list:List<ExpenseEntity>): String {
        var total = 0.0
        list.forEach {
            if(it.type == "expense"){
                total += it.amount
            }

        }
        return "₹ ${total}"

    }

    fun getTotalIncome(list:List<ExpenseEntity>): String {
        var total = 0.0
        list.forEach {
            if (it.type == "income") {
                total += it.amount
            }
        }
        return "₹ ${total}"
    }
    fun getItemIcon(item:ExpenseEntity): Int {
         if(item.type == "Paypal"){
            return R.drawable.ic_paypal
        }
        else if(item.type == "Netflix"){
            return R.drawable.ic_netflix
        }
        else if(item.type == "Starbucks"){
            return R.drawable.ic_starbucks
        }
        return R.drawable.ic_upwork
    }


}
class HomeViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = ExpenseDataBase.getInstance(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}