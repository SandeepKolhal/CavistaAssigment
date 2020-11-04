package com.sk.cavistaassignment.model


import com.google.gson.annotations.SerializedName

data class ImageSearchResponse(
    @SerializedName("data")
    val imageData: List<Data>?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("success")
    val success: Boolean?
)