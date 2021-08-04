package com.example.coins.data.latestListing

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class USD(

	@Json(name="percent_change_30d")
	val percentChange30d: Double? = null,

	@Json(name="percent_change_1h")
	val percentChange1h: Double? = null,

	@Json(name="last_updated")
	val lastUpdated: String? = null,

	@Json(name="percent_change_24h")
	val percentChange24h: Double? = null,

	@Json(name="market_cap")
	val marketCap: Double? = null,

	@Json(name="price")
	val price: Double? = null,

	@Json(name="volume_24h")
	val volume24h: Double? = null,

	@Json(name="percent_change_7d")
	val percentChange7d: Double? = null
) : Parcelable