package com.sk.cavistaassignment.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("datetime")
    val datetime: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
): Parcelable