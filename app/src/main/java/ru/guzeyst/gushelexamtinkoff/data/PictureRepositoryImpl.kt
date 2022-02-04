package ru.guzeyst.gushelexamtinkoff.data

import android.content.Context
import androidx.lifecycle.LiveData
import ru.guzeyst.gushelexamtinkoff.data.database.DataBase
import ru.guzeyst.gushelexamtinkoff.data.database.entity.TypeChapter
import ru.guzeyst.gushelexamtinkoff.data.mapper.PictureMapper
import ru.guzeyst.gushelexamtinkoff.data.network.ApiFactory
import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture

class PictureRepositoryImpl(context: Context): PictureRepository{

    private val pictureMapper = PictureMapper()
    private val picturesDao = DataBase.getInstance(context).pictureDao()

    override suspend fun loadRandomPicture() {
        try {
            val picDto = ApiFactory.apiService.getPictureRandom()
            val picEntity = pictureMapper.dtoToEntity(picDto, TypeChapter.RANDOM)
            picturesDao.insertPicture(picEntity)
        } catch (e: Exception) {
        }
    }

    override fun getPicturesFromDB(): LiveData<List<Picture>> {
        return pictureMapper.entityToPicture(picturesDao.getPicturesList())
    }

}