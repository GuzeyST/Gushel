package ru.guzeyst.gushelexamtinkoff.data

import android.app.Application
import androidx.lifecycle.LiveData
import ru.guzeyst.gushelexamtinkoff.data.database.PicturesDao
import ru.guzeyst.gushelexamtinkoff.data.database.entity.TypeChapter
import ru.guzeyst.gushelexamtinkoff.data.mapper.PictureMapper
import ru.guzeyst.gushelexamtinkoff.data.network.ApiFactory
import ru.guzeyst.gushelexamtinkoff.data.network.ApiService
import ru.guzeyst.gushelexamtinkoff.data.network.model.ResponseDto
import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture
import javax.inject.Inject


class PictureRepositoryImpl @Inject constructor(
    private val application: Application,
    private val pictureMapper: PictureMapper,
    private val picturesDao: PicturesDao
) : PictureRepository {

    private val apiService = ApiFactory.apiService

    override suspend fun loadRandomPicture(): Picture? {
        val pic = try {
            val picDto = apiService.getPictureRandom()
            val picEntity = pictureMapper.dtoToEntity(picDto, TypeChapter.RANDOM)
            picturesDao.insertPicture(picEntity)
            pictureMapper.dtoToPicture(picDto)
        } catch (e: Exception) {
            null
        }
        return pic
    }

    override suspend fun loadLatestPicturesList(pageNumber: Int) {
        val chapter = TypeChapter.LATEST
        loadPicturesList(chapter, pageNumber)
    }

    override suspend fun loadHotPicturesList(pageNumber: Int) {
        val chapter = TypeChapter.HOT
        loadPicturesList(chapter, pageNumber)
    }

    override suspend fun loadTopPicturesList(pageNumber: Int) {
        val chapter = TypeChapter.TOP
        loadPicturesList(chapter, pageNumber)
    }

    override fun getPicturesFromDB(): LiveData<List<Picture>> {
        return pictureMapper.entityToPicture(picturesDao.getPicturesList())
    }

    override fun getLatestListFromDB(): LiveData<List<Picture>> {
        val chapter = TypeChapter.LATEST
        return getListFromDB(chapter)
    }

    override fun getHotListFromDB(): LiveData<List<Picture>> {
        val chapter = TypeChapter.HOT
        return getListFromDB(chapter)
    }

    override fun getTopListFromDB(): LiveData<List<Picture>> {
        val chapter = TypeChapter.TOP
        return getListFromDB(chapter)
    }

    private fun getListFromDB(chapter: TypeChapter): LiveData<List<Picture>> {
        val listItem = picturesDao.getPicturesListForChapter(chapter)
        return pictureMapper.entityToPicture(listItem)
    }

    private suspend fun loadResponse(chapter: TypeChapter, pageNumber: Int): ResponseDto {

        val chapterString = when (chapter) {
            TypeChapter.LATEST -> ApiService.PATH_CHAPTER_LATEST
            TypeChapter.TOP -> ApiService.PATH_CHAPTER_TOP
            TypeChapter.HOT -> ApiService.PATH_CHAPTER_HOT
            else -> throw RuntimeException("??he header type is not supported")
        }

        return apiService.getPicturesList(chapterString, pageNumber)
    }

    private suspend fun loadPicturesList(chapter: TypeChapter, pageNumber: Int) {
        try {
            val response = loadResponse(chapter, pageNumber)
            val listPictItem = pictureMapper.responseDtoToListItem(response, chapter)
            picturesDao.insertList(listPictItem)
        } catch (e: java.lang.Exception) {

        }
    }
}