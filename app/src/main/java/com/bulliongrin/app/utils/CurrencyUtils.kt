package com.bulliongrin.app.utils

import android.content.Context
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

object CurrencyUtils {
    private const val PREFS_NAME = "BullionGrinPrefs"
    private const val KEY_CURRENCY_CODE = "currency_code"

    fun getSelectedCurrencyCode(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_CURRENCY_CODE, "USD") ?: "USD"
    }

    fun saveSelectedCurrencyCode(context: Context, code: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_CURRENCY_CODE, code).apply()
    }

    fun formatAmount(context: Context, amount: Double): String {
        val code = getSelectedCurrencyCode(context)
        return try {
            val format = NumberFormat.getCurrencyInstance()
            format.currency = Currency.getInstance(code)
            format.format(amount)
        } catch (e: Exception) {
            // Fallback to simple formatting if currency code is invalid
            String.format(Locale.getDefault(), "%s %.2f", code, amount)
        }
    }

    fun getCurrencySymbol(context: Context): String {
        val code = getSelectedCurrencyCode(context)
        return try {
            Currency.getInstance(code).symbol
        } catch (e: Exception) {
            code
        }
    }

    fun getAllCurrencies(): List<Pair<String, String>> {
        return Currency.getAvailableCurrencies()
            .sortedBy { it.currencyCode }
            .map { it.currencyCode to "${it.currencyCode} - ${it.displayName}" }
    }
}
