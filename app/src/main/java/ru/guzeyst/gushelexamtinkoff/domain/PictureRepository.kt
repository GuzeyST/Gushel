package ru.guzeyst.gushelexamtinkoff.domain

import androidx.lifecycle.LiveData
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture

interface PictureRepository {
    //network
    suspend fun loadRandomPicture(): Picture?
    suspend fun loadLatestPicturesList(pageNumber: Int)
    suspend fun loadHotPicturesList(pageNumber: Int)
    suspend fun loadTopPicturesList(pageNumber: Int)

    //data base
    fun getPicturesFromDB(): LiveData<List<Picture>>
    fun getLatestListFromDB(): LiveData<List<Picture>>
    fun getHotListFromDB(): LiveData<List<Picture>>
    fun getTopListFromDB(): LiveData<List<Picture>>
}