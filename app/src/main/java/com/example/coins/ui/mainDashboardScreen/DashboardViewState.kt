package com.example.coins.ui.mainDashboardScreen

import androidx.compose.runtime.Immutable
import com.example.coins.data.latestListing.LatestCryptoListing
import com.example.coins.ui.listing.CoinListingFilter.*
import com.example.coins.ui.listing.CoinListingCategory.*

@Immutable
data class DashboardViewState(
    val query: String = "",
    val progress: Boolean = false,
    val filterSelected: String = "",
    val latestCryptoListing: LatestCryptoListing = LatestCryptoListing(listOf()),


)