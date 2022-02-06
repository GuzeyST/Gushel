package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.hotFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.guzeyst.gushelexamtinkoff.data.PictureRepositoryImpl
import ru.guzeyst.gushelexamtinkoff.domain.useCase.database.GetHotListFromDB
import ru.guzeyst.gushelexamtinkoff.domain.useCase.network.LoadHotPicturesList
import javax.inject.Inject

class HotViewModel @Inject constructor(
    private val getHotListFromDB: GetHotListFromDB,
    private val loadHotPicturesList: LoadHotPicturesList
) : ViewModel() {

    val listPictures = getHotListFromDB.invoke()
    private var pageNumber = 0

    init {
        loadImage()
    }

    private fun loadImage() {
        viewModelScope.launch {
            loadHotPicturesList.invoke(pageNumber)
        }
    }

    fun loadNextPage() {
        pageNumber++
        loadImage()
    }
}