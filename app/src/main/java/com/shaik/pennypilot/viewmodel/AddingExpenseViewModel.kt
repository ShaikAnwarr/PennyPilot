package com.shaik.pennypilot.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shaik.pennypilot.data.ExpenseDataBase // Adjust the correct path for your database
import com.shaik.pennypilot.data.dao.ExpenseDao
import com.shaik.pennypilot.data.model.ExpenseEntity
import java.lang.IllegalArgumentException

class AddingExpenseViewModel(val dao: ExpenseDao) : ViewModel() {

    suspend fun addExpense(expenseEntity: ExpenseEntity): Boolean {
        return try {
            dao.insertExpense(expenseEntity)
            true
        } catch (ex: Throwable) {
            false
        }
    }
}

class AddingExpenseViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Ensure modelClass is of type AddingExpenseViewModel
        if (modelClass.isAssignableFrom(AddingExpenseViewModel::class.java)) {
            val dao = ExpenseDataBase.getInstance(context).expenseDao() // Ensure the correct database instance is called
            @Suppress("UNCHECKED_CAST")
            return AddingExpenseViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
