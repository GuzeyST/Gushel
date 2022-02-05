package ru.guzeyst.gushelexamtinkoff.data.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.guzeyst.gushelexamtinkoff.data.database.entity.PictureItem
import ru.guzeyst.gushelexamtinkoff.data.database.entity.TypeChapter
import ru.guzeyst.gushelexamtinkoff.data.network.model.PictureDto
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture

class PictureMapper {

    fun dtoToEntity(dto: PictureDto, type: TypeChapter): PictureItem =
        PictureItem(dto.id, type, dto.description, dto.gifURL)

    fun entityToPicture(entityItem: LiveData<List<PictureItem>>): LiveData<List<Picture>> =
        Transformations.map(entityItem) {
            listPictureItemToListPicture(it)
        }

    private fun listPictureItemToListPicture(fromList: List<PictureItem>): List<Picture> =
        fromList.map {
            Picture(it.id, it.description, it.gifURL)
        }
}