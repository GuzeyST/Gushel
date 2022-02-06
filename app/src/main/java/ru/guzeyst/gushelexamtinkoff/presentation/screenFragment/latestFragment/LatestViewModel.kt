package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.latestFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.guzeyst.gushelexamtinkoff.data.PictureRepositoryImpl
import ru.guzeyst.gushelexamtinkoff.domain.useCase.database.GetHotListFromDB
import ru.guzeyst.gushelexamtinkoff.domain.useCase.database.GetLatestListFromDB
import ru.guzeyst.gushelexamtinkoff.domain.useCase.database.GetPicturesFromDB
import ru.guzeyst.gushelexamtinkoff.domain.useCase.network.LoadHotPicturesList
import ru.guzeyst.gushelexamtinkoff.domain.useCase.network.LoadLatestPicturesList
import ru.guzeyst.gushelexamtinkoff.domain.useCase.network.LoadRandomPicture

class LatestViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = PictureRepositoryImpl(application)
    private val getLatestListFromDB = GetLatestListFromDB(repo)
    private val loadLatestPicturesList = LoadLatestPicturesList(repo)
    val listPictures = getLatestListFromDB.invoke()
    private var pageNumber = 0

    init {
        loadImage()
    }

    private fun loadImage(){
        viewModelScope.launch {
            loadLatestPicturesList.invoke(pageNumber)
        }
    }

    fun loadNextPage(){
        pageNumber++
        loadImage()
    }

}