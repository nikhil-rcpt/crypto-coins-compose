package com.example.coins.ui.discover

import androidx.compose.runtime.Immutable
import com.example.coins.data.discover.PersonalisedModelGroup
import com.example.coins.data.discover.PersonalisedNewsModel
import com.example.coins.data.latestListing.LatestCryptoListing
import com.example.coins.ui.discover.DiscoverListingCategory.*
import com.example.coins.ui.listing.CoinListingFilter.*
import com.example.coins.ui.listing.CoinListingCategory.*

@Immutable
data class DiscoverViewState(
    val query: String = "",
    val progress: Boolean = false,
    val filterSelected: String = Latest().id,
    val selectedListingTab : DiscoverListingCategory= Latest(),
    val trending : PersonalisedNewsModel = PersonalisedNewsModel(),
    val personalisedNewsGroup : PersonalisedModelGroup = PersonalisedModelGroup(listOf()),
    val latestCryptoListing: LatestCryptoListing = LatestCryptoListing(listOf()),

    val listingTabs : List<DiscoverListingCategory> = listOf(
        Latest(),
        Blog(),
        TopGainers(),
        TopLosers()
    )
)