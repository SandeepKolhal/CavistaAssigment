package com.sk.cavistaassignment.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("datetime")
    val datetime: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("images")
    val images: List<Image>?,
    @SerializedName("title")
    val title: String?
)