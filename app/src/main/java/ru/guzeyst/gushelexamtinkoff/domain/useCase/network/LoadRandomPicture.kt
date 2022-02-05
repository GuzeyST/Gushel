package ru.guzeyst.gushelexamtinkoff.domain.useCase.network

import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository

class LoadRandomPicture(private val repo: PictureRepository) {
    suspend operator fun invoke() = repo.loadRandomPicture()
}