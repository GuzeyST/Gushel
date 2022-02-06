package ru.guzeyst.gushelexamtinkoff.domain.useCase.network

import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository
import javax.inject.Inject

class LoadRandomPicture @Inject constructor(private val repo: PictureRepository) {
    suspend operator fun invoke() = repo.loadRandomPicture()
}