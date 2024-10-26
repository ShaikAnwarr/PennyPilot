package com.shaik.pennypilot.di


import android.content.Context
import com.shaik.pennypilot.data.ExpenseDatabase
import com.shaik.pennypilot.data.dao.ExpenseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.internal.Provider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context, ): ExpenseDatabase {
        return ExpenseDatabase.getInstance(context)
    }

    @Provides
    fun provideExpenseDao(database: ExpenseDatabase): ExpenseDao {
        return database.expenseDao()
    }
}