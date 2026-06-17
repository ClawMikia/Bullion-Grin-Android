package com.bulliongrin.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bulliongrin.app.data.entity.SavingsRecord
import com.bulliongrin.app.databinding.ItemRecordBinding
import com.bulliongrin.app.utils.CurrencyUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SavingsRecordAdapter : ListAdapter<SavingsRecord, SavingsRecordAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemRecordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(record: SavingsRecord) {
            val context = itemView.context
            binding.tvAmount.text = CurrencyUtils.formatAmount(context, record.amount)
            binding.tvFrequency.text = record.frequency
            
            val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            binding.tvDate.text = sdf.format(Date(record.date))
            
            if (!record.note.isNullOrBlank()) {
                binding.tvNote.text = record.note
                binding.tvNote.visibility = View.VISIBLE
            } else {
                binding.tvNote.visibility = View.GONE
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<SavingsRecord>() {
        override fun areItemsTheSame(oldItem: SavingsRecord, newItem: SavingsRecord) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: SavingsRecord, newItem: SavingsRecord) = oldItem == newItem
    }
}
