package com.example.coins.data.latestListing

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Platform(

	@Json(name="symbol")
	val symbol: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="token_address")
	val tokenAddress: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="slug")
	val slug: String? = null
) : Parcelable