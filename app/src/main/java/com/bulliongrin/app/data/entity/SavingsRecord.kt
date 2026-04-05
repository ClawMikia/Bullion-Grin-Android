package com.bulliongrin.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savings_records")
data class SavingsRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val date: Long, // timestamp
    val frequency: String, // Daily, Weekly, Monthly, Quarterly, Annually
    val note: String? = null
)
