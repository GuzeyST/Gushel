package ru.guzeyst.gushelexamtinkoff.domain

import androidx.lifecycle.LiveData
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture

interface PictureRepository {
    //network
    suspend fun loadRandomPicture()
    suspend fun loadLatestPicturesList()
    suspend fun loadHotPicturesList()
    suspend fun loadTopPicturesList()

    //data base
    fun getPicturesFromDB(): LiveData<List<Picture>>
    fun getLatestListFromDB(): LiveData<List<Picture>>
    fun getHotListFromDB(): LiveData<List<Picture>>
    fun getTopListFromDB(): LiveData<List<Picture>>
}