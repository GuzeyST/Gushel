package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.hotFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.guzeyst.gushelexamtinkoff.data.PictureRepositoryImpl
import ru.guzeyst.gushelexamtinkoff.domain.useCase.database.GetPicturesFromDB
import ru.guzeyst.gushelexamtinkoff.domain.useCase.network.LoadRandomPicture

class HotViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = PictureRepositoryImpl(application)
    private val getPicturesFromDB = GetPicturesFromDB(repo)
    private val loadRandomPicture = LoadRandomPicture(repo)
    val listPictures = getPicturesFromDB.invoke()

    init {
        viewModelScope.launch {
            loadRandomPicture.invoke()
        }
    }

}