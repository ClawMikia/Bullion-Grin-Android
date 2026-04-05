package com.bulliongrin.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bulliongrin.app.data.database.AppDatabase
import com.bulliongrin.app.data.entity.SavingsRecord
import com.bulliongrin.app.data.entity.UserStats
import com.bulliongrin.app.data.repository.SavingsRepository
import kotlinx.coroutines.launch

class SavingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SavingsRepository
    val allRecords: LiveData<List<SavingsRecord>>
    val totalSaved: LiveData<Double>
    val userStats: LiveData<UserStats>

    init {
        val dao = AppDatabase.getDatabase(application).savingsDao()
        repository = SavingsRepository(dao)
        allRecords = repository.allRecords
        totalSaved = repository.totalSaved
        userStats = repository.userStats
    }

    fun insertRecord(amount: Double, frequency: String, note: String?) {
        viewModelScope.launch {
            val record = SavingsRecord(
                amount = amount,
                date = System.currentTimeMillis(),
                frequency = frequency,
                note = note
            )
            repository.insertRecord(record)
        }
    }

    fun getRecordsInRange(start: Long, end: Long): LiveData<List<SavingsRecord>> {
        return repository.getRecordsInRange(start, end)
    }
}
