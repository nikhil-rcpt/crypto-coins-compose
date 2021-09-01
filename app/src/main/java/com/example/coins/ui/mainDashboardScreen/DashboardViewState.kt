package com.example.coins.ui.mainDashboardScreen

import androidx.compose.runtime.Immutable
import com.example.coins.data.latestListing.LatestCryptoListing
import com.example.coins.ui.mainDashboardScreen.GraphCategory.*
import com.example.coins.ui.mainDashboardScreen.GraphRange.*

@Immutable
data class DashboardViewState(
    val query: String = "",
    val progress: Boolean = false,
    val filterSelected: String = "",
    val selectedGraphOption:GraphCategory = LineChart,
    val selectedGraphRangeOption:GraphRange = _all,
    val assetView : AssetView = AssetView.Value,
    val latestCryptoListing: LatestCryptoListing = LatestCryptoListing(listOf()),
    val graphOptions: List<GraphCategory> = listOf(
        LineChart,
        PieChart,
        Statistics,
    ),
    val graphRangeOptions: List<GraphRange> = listOf(
        _24H,
        _7d,
        _30d,
        _60d,
        _90d,
        _all,
    ),
    val graphDataPoints: List<Int> = dummyGraphDataPoints
)