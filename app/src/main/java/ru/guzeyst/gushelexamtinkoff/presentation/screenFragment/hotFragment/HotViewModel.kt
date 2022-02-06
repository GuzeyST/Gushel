package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.hotFragment

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

class HotViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = PictureRepositoryImpl(application)
    private val getHotListFromDB = GetHotListFromDB(repo)
    private val loadHotPicturesList = LoadHotPicturesList(repo)
    val listPictures = getHotListFromDB.invoke()
    private var pageNumber = 0

    init {
        loadImage()
    }

    private fun loadImage(){
        viewModelScope.launch {
            loadHotPicturesList.invoke(0)
        }
    }

    fun loadNextPage(){
        pageNumber++
        loadImage()
    }

}