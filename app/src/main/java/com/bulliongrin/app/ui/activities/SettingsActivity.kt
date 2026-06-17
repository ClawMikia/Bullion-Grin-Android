package com.bulliongrin.app.ui.activities

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import com.bulliongrin.app.databinding.ActivitySettingsBinding
import com.bulliongrin.app.utils.CurrencyUtils

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupThemeSwitch()
        setupNotificationSwitch()
        setupCurrencyDropdown()
    }

    private fun setupThemeSwitch() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        binding.switchDarkMode.isChecked = prefs.getBoolean(KEY_DARK_MODE, false)

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            savePreference(KEY_DARK_MODE, isChecked)
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun setupNotificationSwitch() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        binding.switchNotifications.isChecked = prefs.getBoolean(KEY_NOTIFICATIONS, true)

        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            savePreference(KEY_NOTIFICATIONS, isChecked)
            // In a real app, we'd enable/disable WorkManager here
        }
    }

    private fun setupCurrencyDropdown() {
        val currencies = CurrencyUtils.getAllCurrencies()
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            currencies.map { it.second },
        )
        binding.actvCurrency.setAdapter(adapter)

        val currentCode = CurrencyUtils.getSelectedCurrencyCode(this)
        val currentIndex = currencies.indexOfFirst { it.first == currentCode }
        if (currentIndex >= 0) {
            binding.actvCurrency.setText(currencies[currentIndex].second, false)
        }

        binding.actvCurrency.setOnItemClickListener { _, _, position, _ ->
            val selectedCode = currencies[position].first
            CurrencyUtils.saveSelectedCurrencyCode(this, selectedCode)
        }
    }

    private fun savePreference(key: String, value: Boolean) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            putBoolean(key, value)
        }
    }

    companion object {
        private const val PREFS_NAME = "BullionGrinPrefs"
        private const val KEY_DARK_MODE = "dark_mode"
        private const val KEY_NOTIFICATIONS = "notifications"
    }
}
