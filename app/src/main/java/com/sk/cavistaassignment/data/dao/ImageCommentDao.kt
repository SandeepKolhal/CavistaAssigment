package com.sk.cavistaassignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sk.cavistaassignment.model.ImageCommentEntity

@Dao
interface ImageCommentDao {

    /**
     * Insert comment in database
     *
     * @param commentEntity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImageComment(commentEntity: ImageCommentEntity)

    /**
     * Query to get all Comments related to Image
     *
     * @param dataID
     * @return
     */
    @Query("SELECT * FROM imageComments WHERE dataId = :dataID  ORDER BY timestamp DESC")
    suspend fun getAllImageComments(dataID: String): List<ImageCommentEntity>

}