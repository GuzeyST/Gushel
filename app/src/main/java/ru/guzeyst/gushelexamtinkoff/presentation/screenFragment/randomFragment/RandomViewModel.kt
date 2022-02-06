package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.randomFragment

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.guzeyst.gushelexamtinkoff.data.PictureRepositoryImpl
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture
import ru.guzeyst.gushelexamtinkoff.domain.useCase.database.GetPicturesFromDB
import ru.guzeyst.gushelexamtinkoff.domain.useCase.network.LoadRandomPicture
import javax.inject.Inject

class RandomViewModel @Inject constructor(
    private val loadRandomPicture: LoadRandomPicture,
    private val getPicturesFromDB: GetPicturesFromDB
) : ViewModel() {

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

    private val _isOnline = MutableLiveData<Boolean>()
    val isOnline: LiveData<Boolean>
        get() = _isOnline

    init {
        loadNextPicture()
    }

    private fun getCurrentPicture() {
        if (!listImage.isEmpty()) {
            _currentPictures.value = listImage[currentIndex]
            _isLastPicture.value = currentIndex == START_INDEX || currentIndex == 0
        }
    }

    private fun loadNextPicture() {
        viewModelScope.launch {
            _isLoading.value = true
            val pic = loadRandomPicture.invoke()
            if(pic != null) {
                _isOnline.value = true
                listImage.add(pic)
                currentIndex++
                getCurrentPicture()
            }else{
                _isOnline.value = false
            }
            _isLoading.value = false
        }
    }

    fun getNextImage() {
        if (currentIndex == listImage.size - 1 || listImage.isEmpty()) {
            loadNextPicture()
        } else {
            getCurrentPicture()
        }
    }

    fun getPreviousPicture() {
        currentIndex--
        getCurrentPicture()
    }

    companion object {
        private const val START_INDEX = -1
    }
}