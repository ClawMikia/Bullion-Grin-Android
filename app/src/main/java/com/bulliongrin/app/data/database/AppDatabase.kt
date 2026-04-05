package com.bulliongrin.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bulliongrin.app.data.dao.SavingsDao
import com.bulliongrin.app.data.entity.SavingsRecord
import com.bulliongrin.app.data.entity.UserStats

@Database(entities = [SavingsRecord::class, UserStats::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun savingsDao(): SavingsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bullion_grin_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
