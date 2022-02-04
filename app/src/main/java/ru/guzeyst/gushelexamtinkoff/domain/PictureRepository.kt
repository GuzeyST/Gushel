package ru.guzeyst.gushelexamtinkoff.domain

import androidx.lifecycle.LiveData
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture

interface PictureRepository {
    suspend fun loadRandomPicture()
    fun getPicturesFromDB(): LiveData<List<Picture>>
}