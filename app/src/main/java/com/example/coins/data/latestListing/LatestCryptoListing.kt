package com.example.coins.data.latestListing

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class LatestCryptoListing(

	@Json(name="data")
	val data: List<CoinDataItem?>? = null,

	@Json(name="status")
	val status: Status? = null
) : Parcelable