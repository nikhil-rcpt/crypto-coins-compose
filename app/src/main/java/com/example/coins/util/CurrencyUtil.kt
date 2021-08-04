package com.example.coins.util

import kotlin.math.round


sealed class CurrencyType(type: String) {
        object INR : CurrencyType("INR")
        object USD : CurrencyType("USD")
}


fun String.toRupee() = "\u20b9$this"


fun getFormattedCoinPrice(currency: CurrencyType = CurrencyType.INR, coinPrice: Double?): String {
       return when (currency) {
                CurrencyType.INR -> getFormattedPrice(coinPrice).toRupee()
                CurrencyType.USD -> { ""
                }
                else -> { "" }
        }
}

fun getFormattedPrice(coinPrice: Double?): String {
        val m = 1000000
        val b = 1000000000
        val t = 1000000000000
        coinPrice ?: return "NA"

        return when {
                coinPrice < m -> coinPrice.roundTo(2).toString()
                coinPrice < b -> "${coinPrice.times(100).div(m).times(0.01).roundTo(2)} M"
                coinPrice < t -> "${coinPrice.times(100).div(b).times(0.01).roundTo(2)} B"
                else -> "${coinPrice.times(100).div(t).times(0.01).roundTo(2)} T"
        }
}


fun Double.roundTo(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}