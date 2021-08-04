package com.example.coins.ui.listing

import androidx.annotation.StringRes
import com.example.coins.R
import com.example.coins.util.CurrencyType


sealed class ListingFilterChangeTimeline(id: String) {
    object hour : ListingFilterChangeTimeline("one-hour")
    object day : ListingFilterChangeTimeline("one-day")
    object week : ListingFilterChangeTimeline("one-week")
}

sealed class CoinListingFilter(val id: String) {

    data class Starred(val isStarred: Boolean = false) : CoinListingFilter("starred")
    data class Currency(val currency: CurrencyType = CurrencyType.INR) : CoinListingFilter("currency")

    data class SortRank(val ascending: Boolean = false) : CoinListingFilter("sort-rank")
    data class TimelineChange(val changeTimeLine: ListingFilterChangeTimeline = ListingFilterChangeTimeline.hour) :
        CoinListingFilter("percent-change")

    object TopHundred : CoinListingFilter("top-hundred")
    object AllCrypto : CoinListingFilter("all-crypto")


    fun getDisplayName(): IntArray {
        return when(this){
            is Starred -> intArrayOf()
            is Currency -> intArrayOf(R.string.inr)
            is SortRank -> intArrayOf(R.string.sort_by_rank)
            is TimelineChange -> intArrayOf(R.string.change_one_week)
            is TopHundred -> intArrayOf(R.string.top_100)
            is AllCrypto -> intArrayOf(R.string.all,R.string.cryptocurrencies)
        }
    }
}


sealed class CoinListingCategory(val id: String,@StringRes val res : Int) {
    data class CryptoAssets(val a: Int = 0) : CoinListingCategory("cryptoassets", R.string.crypto_assets)
    data class Exchanges(val a: Int = 0) : CoinListingCategory("exchanges",R.string.exchanges)
    data class Sectors(val a: Int = 0) : CoinListingCategory("sectors",R.string.sectors)

}