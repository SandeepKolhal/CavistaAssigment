package com.sk.cavistaassignment.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "imageComments")
data class ImageCommentEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "comment")
    @SerializedName("comment")
    val comment: String?,

    @ColumnInfo(name = "dataId")
    @SerializedName("dataId")
    val dataId: String?,

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    val timestamp: Long
)