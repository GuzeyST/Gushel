package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.topFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.guzeyst.gushelexamtinkoff.domain.useCase.database.GetTopListFromDB
import ru.guzeyst.gushelexamtinkoff.domain.useCase.network.LoadTopPicturesList
import javax.inject.Inject

class TopViewModel @Inject constructor(
    private val getTopListFromDB: GetTopListFromDB,
    private val loadTopPicturesList: LoadTopPicturesList
) : ViewModel() {

    val listPictures = getTopListFromDB.invoke()
    private var pageNumber = 0

    init {
        loadImage()
    }

    private fun loadImage() {
        viewModelScope.launch {
            loadTopPicturesList.invoke(pageNumber)
        }
    }

    fun loadNextPage() {
        pageNumber++
        loadImage()
    }


}