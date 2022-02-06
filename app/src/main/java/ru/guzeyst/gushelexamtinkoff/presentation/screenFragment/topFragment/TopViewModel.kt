package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.topFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.guzeyst.gushelexamtinkoff.data.PictureRepositoryImpl
import ru.guzeyst.gushelexamtinkoff.domain.useCase.database.GetTopListFromDB
import ru.guzeyst.gushelexamtinkoff.domain.useCase.network.LoadTopPicturesList

class TopViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = PictureRepositoryImpl(application)
    private val getTopListFromDB = GetTopListFromDB(repo)
    private val loadTopPicturesList = LoadTopPicturesList(repo)
    val listPictures = getTopListFromDB.invoke()
    private var pageNumber = 0


    init {
        loadImage()

    }

    private fun loadImage() {
        viewModelScope.launch {
            loadTopPicturesList.invoke(0)
        }
    }

    fun loadNextPage() {
        pageNumber++
        loadImage()
    }


}