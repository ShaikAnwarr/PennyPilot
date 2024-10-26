package com.shaik.pennypilot.viewmodel

import com.shaik.pennypilot.data.dao.ExpenseDao
import com.shaik.pennypilot.data.model.ExpenseSummary
import com.shaik.pennypilot.utils.Utils
import com.shaik.pennypilot.viewmodel.BaseViewModel
import com.shaik.pennypilot.viewmodel.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import com.github.mikephil.charting.data.Entry
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(val dao: ExpenseDao) : BaseViewModel() {
    val entries = dao.getAllExpenseByDate()
    val topEntries = dao.getTopExpenses()

    // Change return type from List<KeyStore.Entry> to List<Entry>
    fun getEntriesForChart(entries: List<ExpenseSummary>): List<Entry> {
        val list = mutableListOf<Entry>()
        for (entry in entries) {
            val formattedDate = Utils.getMillisFromDate(entry.date)
            list.add(Entry(formattedDate.toFloat(), entry.total_amount.toFloat()))
        }
        return list
    }

    override fun onEvent(event: UiEvent) {
        // Handle events if necessary
    }
}
