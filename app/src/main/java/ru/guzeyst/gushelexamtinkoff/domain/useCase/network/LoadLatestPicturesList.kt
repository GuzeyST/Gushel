package ru.guzeyst.gushelexamtinkoff.domain.useCase.network

import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository

class LoadLatestPicturesList(private val repo: PictureRepository, val pageNumber: Int) {
    suspend operator fun invoke() = repo.loadLatestPicturesList(pageNumber)
}