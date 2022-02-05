package ru.guzeyst.gushelexamtinkoff.domain.useCase.network

import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository

class LoadTopPicturesList(private val repo: PictureRepository) {
    suspend operator fun invoke() = repo.loadTopPicturesList()
}