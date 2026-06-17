package com.bulliongrin.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class UserStats(
    @PrimaryKey val id: Int = 1,
    val streak: Int = 0,
    val lastSaveDate: Long = 0,
    val totalSaved: Double = 0.0,
    val xp: Int = 0,
    val level: Int = 1,
    val achievements: String = "", // Comma-separated achievement IDs
)
