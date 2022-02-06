package ru.guzeyst.gushelexamtinkoff.domain.useCase.network

import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository

class LoadHotPicturesList(private val repo: PictureRepository) {
    suspend operator fun invoke(pageNumber: Int) = repo.loadHotPicturesList(pageNumber)
}