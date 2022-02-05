package ru.guzeyst.gushelexamtinkoff.domain.useCase.network

import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository

class LoadHotPicturesList(private val repo: PictureRepository, val pageNumber: Int) {
    suspend operator fun invoke() = repo.loadHotPicturesList(pageNumber)
}