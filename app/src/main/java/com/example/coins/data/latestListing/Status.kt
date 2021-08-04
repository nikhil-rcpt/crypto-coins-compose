package com.example.coins.data.latestListing

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Status(

	@Json(name="error_message")
	val errorMessage: String? = null,

	@Json(name="elapsed")
	val elapsed: Int? = null,

	@Json(name="total_count")
	val totalCount: Int? = null,

	@Json(name="credit_count")
	val creditCount: Int? = null,

	@Json(name="error_code")
	val errorCode: Int? = null,

	@Json(name="timestamp")
	val timestamp: String? = null,

	@Json(name="notice")
	val notice: String? = null
) : Parcelable