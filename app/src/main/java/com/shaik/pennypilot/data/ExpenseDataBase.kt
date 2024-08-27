package com.shaik.pennypilot.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.shaik.pennypilot.data.dao.ExpenseDao
import com.shaik.pennypilot.data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDataBase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseDataBase? = null

        fun getDatabase(context: Context): ExpenseDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,  // Use application context to avoid memory leaks
                    ExpenseDataBase::class.java,
                    DATABASE_NAME
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        InitBasicData(context)  // Pass context to the initialization function
                    }

                    // Corrected the InitBasicData function
                    fun InitBasicData(context: Context) {
                        // Launch a coroutine to insert initial data asynchronously
                        CoroutineScope(Dispatchers.IO).launch {
                            // Get the database instance and insert initial data
                            val expenseDao = getDatabase(context).expenseDao()
                            expenseDao.insertExpense(ExpenseEntity(1, "Salary", 50000.70, System.currentTimeMillis(), "Salary", "Income"))
                            expenseDao.insertExpense(ExpenseEntity(2, "Paypal", 2000.80, System.currentTimeMillis(), "Paypal", "Income"))
                            expenseDao.insertExpense(ExpenseEntity(3, "Netflix", 1500.98, System.currentTimeMillis(), "Netflix", "Expense"))
                            expenseDao.insertExpense(ExpenseEntity(4, "Starbucks", 800.19, System.currentTimeMillis(), "Starbucks", "Expense"))
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }

        private const val DATABASE_NAME = "expense_db"
    }
}
