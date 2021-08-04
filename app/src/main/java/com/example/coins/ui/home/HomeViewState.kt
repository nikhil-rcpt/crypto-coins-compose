package com.example.coins.ui.home

import androidx.compose.runtime.Immutable
import com.example.coins.data.latestListing.LatestCryptoListing
import com.example.coins.ui.home.HomeActionWidget.*
import com.example.coins.ui.home.HomeCoinListType.*

@Immutable
data class HomeViewState(
    val progress: Boolean = false,
    val latestCryptoListing: LatestCryptoListing = LatestCryptoListing(listOf()),
    val actionsWidget: List<HomeActionWidget> = listOf(
        PriceAlert(), Compare(), Convert(), Watchlist()
    ),
    val homeCoinList: List<HomeCoinListType> = listOf(
        TopCoins(listOf()), GainersAndLosers(listOf()),GainersAndLosers(listOf())
    )
){

}