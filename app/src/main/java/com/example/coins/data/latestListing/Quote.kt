package com.example.coins.data.latestListing

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Quote(

	@Json(name="USD")
	val usd: USD? = null,

	@Json(name="INR")
	val inr: USD? = null,

) : Parcelable