package com.example.coins.ui.profile

import androidx.compose.runtime.Immutable
import com.example.coins.data.discover.PersonalisedModelGroup
import com.example.coins.data.discover.PersonalisedNewsModel
import com.example.coins.data.latestListing.LatestCryptoListing
import com.example.coins.data.profile.ProfileModel
import com.example.coins.data.profile.SettingsGroup
import com.example.coins.ui.discover.DiscoverListingCategory.*
import com.example.coins.ui.listing.CoinListingFilter.*
import com.example.coins.ui.listing.CoinListingCategory.*

@Immutable
data class ProfileViewState(
    val query: String = "",
    val progress: Boolean = false,
    val filterSelected: String = Latest().id,
    val profileSettingsGroup: ProfileModel = ProfileModel(listOf()),
    val trending : PersonalisedNewsModel = PersonalisedNewsModel(),
    val personalisedNewsGroup : PersonalisedModelGroup = PersonalisedModelGroup(listOf()),
    val latestCryptoListing: LatestCryptoListing = LatestCryptoListing(listOf()),
    val socialMediaLinks : List<SocialMediaType> = listOf(SocialMediaType.website(""),SocialMediaType.reddit,SocialMediaType.facebook,SocialMediaType.twitter)

) {
}