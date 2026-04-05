package com.bulliongrin.app.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bulliongrin.app.databinding.ActivityMainBinding
import com.bulliongrin.app.utils.CurrencyUtils
import com.bulliongrin.app.viewmodel.SavingsViewModel
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SavingsViewModel by viewModels()
    private val PREFS_NAME = "BullionGrinPrefs"
    private val KEY_DARK_MODE = "dark_mode"

    override fun onCreate(savedInstanceState: Bundle?) {
        applyTheme()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        // Refresh UI in case currency was changed in Settings
        refreshUI()
    }

    private fun refreshUI() {
        val total = viewModel.totalSaved.value ?: 0.0
        binding.tvTotalSaved.text = CurrencyUtils.formatAmount(this, total)
    }

    private fun applyTheme() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkMode = prefs.getBoolean(KEY_DARK_MODE, false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun setupObservers() {
        viewModel.totalSaved.observe(this) { total ->
            binding.tvTotalSaved.text = CurrencyUtils.formatAmount(this, total ?: 0.0)
        }

        viewModel.userStats.observe(this) { stats ->
            if (stats != null) {
                binding.tvStreak.text = "${stats.streak} 🔥"
                binding.tvXP.text = "${stats.xp} XP"
            }
        }
    }

    private fun setupListeners() {
        binding.btnAddContribution.setOnClickListener {
            startActivity(Intent(this, AddContributionActivity::class.java))
        }

        binding.btnViewRecords.setOnClickListener {
            startActivity(Intent(this, RecordsActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
