package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.latestFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.guzeyst.gushelexamtinkoff.domain.useCase.database.GetLatestListFromDB
import ru.guzeyst.gushelexamtinkoff.domain.useCase.network.LoadLatestPicturesList
import javax.inject.Inject

class LatestViewModel @Inject constructor(
    private val getLatestListFromDB: GetLatestListFromDB,
    private val loadLatestPicturesList: LoadLatestPicturesList
) : ViewModel() {
    val listPictures = getLatestListFromDB.invoke()
    private var pageNumber = 0

    init {
        loadImage()
    }

    private fun loadImage() {
        viewModelScope.launch {
            loadLatestPicturesList.invoke(pageNumber)
        }
    }

    fun loadNextPage() {
        pageNumber++
        loadImage()
    }

}