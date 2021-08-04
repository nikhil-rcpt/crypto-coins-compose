package com.example.coins.data.latestListing

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CoinDataItem(

	@Json(name="symbol")
	val symbol: String? = null,

	@Json(name="circulating_supply")
	val circulatingSupply: Double? = null,

	@Json(name="last_updated")
	val lastUpdated: String? = null,

	@Json(name="total_supply")
	val totalSupply: Double? = null,

	@Json(name="cmc_rank")
	val cmcRank: Int? = null,

	@Json(name="platform")
	val platform: Platform? = null,

	@Json(name="tags")
	val tags: List<String?>? = null,

	@Json(name="date_added")
	val dateAdded: String? = null,

	@Json(name="quote")
	val quote: Quote? = null,

	@Json(name="num_market_pairs")
	val numMarketPairs: Int? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="max_supply")
	val maxSupply: Double? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="slug")
	val slug: String? = null
) : Parcelable