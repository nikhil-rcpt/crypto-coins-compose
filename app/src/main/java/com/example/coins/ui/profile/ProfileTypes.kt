package com.example.coins.ui.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.coins.R
import com.example.coins.util.CurrencyType


sealed class DiscoverFilter(val id: String) {
//
//    data class Starred(val isStarred: Boolean = false) : CoinListingFilter("starred")
//    data class Currency(val currency: CurrencyType = CurrencyType.INR) : CoinListingFilter("currency")
//
//    object TopHundred : CoinListingFilter("top-hundred")
//    object AllCrypto : CoinListingFilter("all-crypto")
//
//
//    fun getDisplayName(): IntArray {
//        return when(this){
//            is Starred -> intArrayOf()
//            is Currency -> intArrayOf(R.string.inr)
//            is SortRank -> intArrayOf(R.string.sort_by_rank)
//            is TimelineChange -> intArrayOf(R.string.change_one_week)
//            is TopHundred -> intArrayOf(R.string.top_100)
//            is AllCrypto -> intArrayOf(R.string.all,R.string.cryptocurrencies)
//        }
}


sealed class DiscoverListingCategory(val id: String, @StringRes val res: Int) {
    data class Latest(val a: Int = 0) : DiscoverListingCategory("latest", R.string.latest_news)
    data class Blog(val a: Int = 0) : DiscoverListingCategory("blog", R.string.blog)
    data class TopGainers(val a: Int = 0) :
        DiscoverListingCategory("top-gainers", R.string.top_gainers)

    data class TopLosers(val a: Int = 0) :
        DiscoverListingCategory("top-losers", R.string.top_losers)

}

sealed class SocialMediaType(val id: String, @DrawableRes val res: Int, val redirectUrl: String) {
    data class website(val url: String) : SocialMediaType("wesbsite", R.drawable.ic_profile_network, "")
    object reddit : SocialMediaType("wesbsite", R.drawable.ic_reddit, "")
    object facebook : SocialMediaType("wesbsite", R.drawable.ic_fb, "")
    object twitter : SocialMediaType("wesbsite", R.drawable.ic_twitter, "")
}