package com.bulliongrin.app.ui.activities

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.bulliongrin.app.databinding.ActivitySettingsBinding
import com.bulliongrin.app.utils.CurrencyUtils
import com.bulliongrin.app.worker.SavingsWorker
import java.util.concurrent.TimeUnit

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val PREFS_NAME = "BullionGrinPrefs"
    private val KEY_DARK_MODE = "dark_mode"
    private val KEY_NOTIFICATIONS = "notifications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        binding.switchDarkMode.isChecked = prefs.getBoolean(KEY_DARK_MODE, false)
        binding.switchNotifications.isChecked = prefs.getBoolean(KEY_NOTIFICATIONS, false)

        setupCurrencySpinner()
    }

    private fun setupCurrencySpinner() {
        val currencies = CurrencyUtils.getAllCurrencies()
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            currencies.map { it.second }
        )
        binding.actvCurrency.setAdapter(adapter)

        val currentCode = CurrencyUtils.getSelectedCurrencyCode(this)
        val currentIndex = currencies.indexOfFirst { it.first == currentCode }
        if (currentIndex != -1) {
            binding.actvCurrency.setText(currencies[currentIndex].second, false)
        }

        binding.actvCurrency.setOnItemClickListener { _, _, position, _ ->
            val selectedCode = currencies[position].first
            CurrencyUtils.saveSelectedCurrencyCode(this, selectedCode)
        }
    }

    private fun setupListeners() {
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            savePreference(KEY_DARK_MODE, isChecked)
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            savePreference(KEY_NOTIFICATIONS, isChecked)
            if (isChecked) {
                scheduleReminders()
            } else {
                cancelReminders()
            }
        }
    }

    private fun savePreference(key: String, value: Boolean) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(key, value).apply()
    }

    private fun scheduleReminders() {
        val workRequest = PeriodicWorkRequestBuilder<SavingsWorker>(24, TimeUnit.HOURS)
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "savings_reminder",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    private fun cancelReminders() {
        WorkManager.getInstance(this).cancelUniqueWork("savings_reminder")
    }
}
