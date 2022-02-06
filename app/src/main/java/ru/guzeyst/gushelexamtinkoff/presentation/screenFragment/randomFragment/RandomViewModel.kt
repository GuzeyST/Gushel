package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.randomFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.guzeyst.gushelexamtinkoff.data.PictureRepositoryImpl
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture
import ru.guzeyst.gushelexamtinkoff.domain.useCase.network.LoadRandomPicture
import kotlin.math.max
import kotlin.math.min

class RandomViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = PictureRepositoryImpl(application)
    private val loadRandomPicture = LoadRandomPicture(repo)
    private val listImage = mutableListOf<Picture>()
    private var currentIndex = START_INDEX

    private val _currentPictures = MutableLiveData<Picture>()
    val currentPictures: LiveData<Picture>
        get() = _currentPictures

    private val _isLastPicture = MutableLiveData<Boolean>()
    val isLastPicture: LiveData<Boolean>
        get() = _isLastPicture

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        loadNextPicture()
    }

    private fun getCurrentPicture() {
        _currentPictures.value = listImage[currentIndex]
        _isLastPicture.value = currentIndex == START_INDEX
    }

    private fun loadNextPicture() {
        viewModelScope.launch {
            _isLoading.value = true
            listImage.add(loadRandomPicture.invoke())
            getCurrentPicture()
            _isLoading.value = false
        }
    }

    fun getNextImage() {
        currentIndex++
        if (currentIndex == listImage.size) {
            loadNextPicture()
        } else {
            getCurrentPicture()
        }
    }

    fun getPreviousPivture() {
        currentIndex--

        getCurrentPicture()
    }

    companion object {
        private const val START_INDEX = 0
    }
}