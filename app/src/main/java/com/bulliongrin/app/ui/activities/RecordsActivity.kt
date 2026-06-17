package com.bulliongrin.app.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bulliongrin.app.databinding.ActivityRecordsBinding
import com.bulliongrin.app.ui.adapters.SavingsRecordAdapter
import com.bulliongrin.app.viewmodel.SavingsViewModel

class RecordsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordsBinding
    private val viewModel: SavingsViewModel by viewModels()
    private lateinit var adapter: SavingsRecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        setupFilters()
    }

    private fun setupRecyclerView() {
        adapter = SavingsRecordAdapter()
        binding.rvRecords.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.allRecords.observe(this) { records ->
            if (records.isNullOrEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.rvRecords.visibility = View.GONE
            } else {
                binding.tvEmpty.visibility = View.GONE
                binding.rvRecords.visibility = View.VISIBLE
                adapter.submitList(records)
            }
        }
    }

    private fun setupFilters() {
        binding.cgFilters.setOnCheckedStateChangeListener { _, checkedIds ->
            val records = viewModel.allRecords.value ?: return@setOnCheckedStateChangeListener
            val checkedId = checkedIds.firstOrNull()

            val filteredRecords = when (checkedId) {
                binding.chipDaily.id -> records.filter { it.frequency == "Daily" }
                binding.chipWeekly.id -> records.filter { it.frequency == "Weekly" }
                binding.chipMonthly.id -> records.filter { it.frequency == "Monthly" }
                else -> records
            }
            adapter.submitList(filteredRecords)
            
            if (filteredRecords.isEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.rvRecords.visibility = View.GONE
            } else {
                binding.tvEmpty.visibility = View.GONE
                binding.rvRecords.visibility = View.VISIBLE
            }
        }
    }
}
