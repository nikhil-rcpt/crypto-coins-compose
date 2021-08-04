package com.example.coins.ui.listing

import androidx.compose.runtime.Immutable
import com.example.coins.data.latestListing.LatestCryptoListing
import com.example.coins.ui.listing.CoinListingFilter.*
import com.example.coins.ui.listing.CoinListingCategory.*

@Immutable
data class ListingViewState(
    val query: String = "",
    val progress: Boolean = false,
    val filterSelected: String = "",
    val selectedListingTab : CoinListingCategory= CryptoAssets(),
    val latestCryptoListing: LatestCryptoListing = LatestCryptoListing(listOf()),
    val listingFilters: List<CoinListingFilter> = listOf(
        Starred(),
        Currency(),
        SortRank(),
        TimelineChange(),
        TopHundred,
        AllCrypto
    ),
    val listingTabs : List<CoinListingCategory> = listOf(
        CryptoAssets(),
        Exchanges(),
        Sectors()
    )
)