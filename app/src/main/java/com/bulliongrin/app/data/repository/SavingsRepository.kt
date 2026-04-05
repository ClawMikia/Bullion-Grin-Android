package com.bulliongrin.app.data.repository

import androidx.lifecycle.LiveData
import com.bulliongrin.app.data.dao.SavingsDao
import com.bulliongrin.app.data.entity.SavingsRecord
import com.bulliongrin.app.data.entity.UserStats

class SavingsRepository(private val dao: SavingsDao) {

    val allRecords: LiveData<List<SavingsRecord>> = dao.getAllRecords()
    val totalSaved: LiveData<Double> = dao.getTotalSaved()
    val userStats: LiveData<UserStats> = dao.getUserStats()

    suspend fun insertRecord(record: SavingsRecord) {
        dao.insertRecord(record)
        updateUserStats(record.amount)
    }

    private suspend fun updateUserStats(amount: Double) {
        val currentStats = dao.getUserStatsSync() ?: UserStats()
        
        // Gamification Logic
        val currentTime = System.currentTimeMillis()
        val oneDayMillis = 24 * 60 * 60 * 1000L
        
        var newStreak = currentStats.streak
        if (currentStats.lastSaveDate > 0) {
            val diff = currentTime - currentStats.lastSaveDate
            if (diff <= oneDayMillis * 2) { // If saved within 48 hours, keep streak
                if (diff >= oneDayMillis) { // If at least 24 hours passed, increment
                    newStreak++
                }
            } else {
                newStreak = 1 // Reset streak if more than 48 hours passed
            }
        } else {
            newStreak = 1 // First save
        }

        // XP System: 10 XP per save + bonus for amount
        val newXp = currentStats.xp + 10 + (amount / 10).toInt()
        
        // Achievements (Simple logic)
        val currentAchievements = currentStats.achievements.split(",").toMutableList()
        if (currentAchievements.isEmpty() || !currentAchievements.contains("first_save")) {
            currentAchievements.add("first_save")
        }
        if (newStreak >= 7 && !currentAchievements.contains("streak_7")) {
            currentAchievements.add("streak_7")
        }
        if (currentStats.totalSaved + amount >= 1000 && !currentAchievements.contains("milestone_1000")) {
            currentAchievements.add("milestone_1000")
        }

        val updatedStats = currentStats.copy(
            xp = newXp,
            streak = newStreak,
            lastSaveDate = currentTime,
            totalSaved = currentStats.totalSaved + amount,
            achievements = currentAchievements.filter { it.isNotEmpty() }.joinToString(",")
        )
        
        dao.insertOrUpdateStats(updatedStats)
    }

    fun getRecordsInRange(start: Long, end: Long): LiveData<List<SavingsRecord>> {
        return dao.getRecordsInDateRange(start, end)
    }
}
