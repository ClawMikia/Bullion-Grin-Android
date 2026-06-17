package com.bulliongrin.app.ui.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bulliongrin.app.databinding.ActivityAddContributionBinding
import com.bulliongrin.app.viewmodel.SavingsViewModel

class AddContributionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContributionBinding
    private val viewModel: SavingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContributionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdown()
        setupListeners()
    }

    private fun setupDropdown() {
        val frequencies = arrayOf("Daily", "Weekly", "Monthly", "Quarterly", "Annually")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, frequencies)
        binding.actvFrequency.setAdapter(adapter)
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            saveContribution()
        }
    }

    private fun saveContribution() {
        val amountStr = binding.etAmount.text.toString()
        val note = binding.etNote.text.toString()
        val frequency = binding.actvFrequency.text.toString()

        if (amountStr.isBlank()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountStr.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            return
        }

        if (frequency.isBlank()) {
            Toast.makeText(this, "Please select a frequency", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.insertRecord(amount, frequency, note)
        finish()
    }
}
