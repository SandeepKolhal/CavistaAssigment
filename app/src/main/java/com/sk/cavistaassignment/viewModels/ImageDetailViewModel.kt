package com.sk.cavistaassignment.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sk.cavistaassignment.data.database.AppDatabase
import com.sk.cavistaassignment.model.ImageCommentEntity
import com.sk.cavistaassignment.repositories.ImageCommentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageDetailViewModel(application: Application) : AndroidViewModel(application) {

    val imageCommentList = MutableLiveData<List<ImageCommentEntity>>()
    private val imageCommentsRepository: ImageCommentsRepository

    init {
        val db = AppDatabase.getDatabase(application)
        imageCommentsRepository = ImageCommentsRepository(db.imageCommentDao())
    }


    fun getAllImageComment(dataID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            imageCommentList.postValue(imageCommentsRepository.getAllImageComments(dataID))
        }
    }

    fun insertComment(commentEntity: ImageCommentEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            imageCommentsRepository.insertComment(commentEntity)
            commentEntity.dataId?.let { getAllImageComment(it) }
        }
    }


}