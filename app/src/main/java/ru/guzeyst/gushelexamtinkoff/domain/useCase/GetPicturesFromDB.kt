package ru.guzeyst.gushelexamtinkoff.domain.useCase


import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository

class GetPicturesFromDB(private val repo: PictureRepository) {
    operator fun invoke() = repo.getPicturesFromDB()
}