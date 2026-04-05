package com.bulliongrin.app.ui.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bulliongrin.app.R
import com.bulliongrin.app.databinding.ActivityAddContributionBinding
import com.bulliongrin.app.utils.CurrencyUtils
import com.bulliongrin.app.viewmodel.SavingsViewModel

class AddContributionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContributionBinding
    private val viewModel: SavingsViewModel by viewModels()

    private val frequencies = arrayOf("Daily", "Weekly", "Monthly", "Quarterly", "Annually")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContributionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, frequencies)
        binding.actvFrequency.setAdapter(adapter)
        binding.actvFrequency.setText(frequencies[0], false)

        // Set currency symbol in TextInputLayout
        binding.tilAmount.prefixText = CurrencyUtils.getCurrencySymbol(this)
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            saveRecord()
        }
    }

    private fun saveRecord() {
        val amountStr = binding.etAmount.text.toString()
        val frequency = binding.actvFrequency.text.toString()
        val note = binding.etNote.text.toString()

        if (amountStr.isBlank()) {
            binding.tilAmount.error = "Please enter an amount"
            return
        }

        val amount = amountStr.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            binding.tilAmount.error = "Please enter a valid amount"
            return
        }

        viewModel.insertRecord(amount, frequency, note)
        Toast.makeText(this, "Contribution saved successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
