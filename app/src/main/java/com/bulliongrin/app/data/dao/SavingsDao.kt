package com.bulliongrin.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bulliongrin.app.data.entity.SavingsRecord
import com.bulliongrin.app.data.entity.UserStats

@Dao
interface SavingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: SavingsRecord): Long

    @Query("SELECT * FROM savings_records ORDER BY date DESC")
    fun getAllRecords(): LiveData<List<SavingsRecord>>

    @Query("SELECT * FROM savings_records WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    fun getRecordsInDateRange(startDate: Long, endDate: Long): LiveData<List<SavingsRecord>>

    @Query("SELECT SUM(amount) FROM savings_records")
    fun getTotalSaved(): LiveData<Double>

    // User Stats
    @Query("SELECT * FROM user_stats WHERE id = 1")
    fun getUserStats(): LiveData<UserStats>

    @Query("SELECT * FROM user_stats WHERE id = 1")
    suspend fun getUserStatsSync(): UserStats?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateStats(stats: UserStats)

    @Update
    suspend fun updateStats(stats: UserStats)
}
