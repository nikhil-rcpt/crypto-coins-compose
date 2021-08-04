package com.example.coins.ui.home

import androidx.annotation.StringRes
import com.example.coins.R
import com.example.coins.data.latestListing.CoinDataItem


sealed class HomeActionWidget(val id: String, @StringRes val labelRes: Int, val imgRes : Int) {
    data class PriceAlert(val a: Int = 0) : HomeActionWidget("priceAlert", R.string.price_alert,R.drawable.ic_price_alerts)
    data class Convert(val a: Int = 0) : HomeActionWidget("convert", R.string.convert,R.drawable.ic_transfer)
    data class Compare(val a: Int = 0) : HomeActionWidget("compare", R.string.compare,R.drawable.ic_compare)
    data class Watchlist(val a: Int = 0) : HomeActionWidget("watchlist", R.string.watchlist,R.drawable.ic_watchlist)
}


sealed class HomeCoinListType(val id: String, @StringRes val labelRes: Int) {
    data class TopCoins(val  items : List<CoinDataItem>) : HomeCoinListType("topCoins", R.string.top_coins)
    data class GainersAndLosers(val  items : List<CoinDataItem>) : HomeCoinListType("gainersAndLosers", R.string.gainers_n_losers)

    fun updateItems(items : List<CoinDataItem>) : HomeCoinListType{
        return when (this) {
            is TopCoins -> if (this.items==items) this else TopCoins(items)
            is GainersAndLosers -> if (this.items==items) this else GainersAndLosers(items)
        }
    }

    fun getCoins()  : List<CoinDataItem>{
        return when (this) {
            is TopCoins -> this.items
            is GainersAndLosers -> this.items
        }
    }
}