package com.tooltrip.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Interchange(
    @Json(name = "title")
    var name: String?,
    @Json(name = "price")
    var price: Int
) : Parcelable