package com.sk.cavistaassignment.repositories

import com.sk.cavistaassignment.data.dao.ImageCommentDao
import com.sk.cavistaassignment.model.ImageCommentEntity

class ImageCommentsRepository(private val dao: ImageCommentDao) {

    /**
     * insert new Image comment in database
     *
     * @param commentEntity
     */
    suspend fun insertComment(commentEntity: ImageCommentEntity) {
        dao.insertImageComment(commentEntity)
    }

    /**
     * get All Comment related to Image
     *
     * @param dataID
     * @return
     */
    suspend fun getAllImageComments(dataID: String): List<ImageCommentEntity> {
        return dao.getAllImageComments(dataID)
    }
}