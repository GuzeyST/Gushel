package ru.guzeyst.gushelexamtinkoff.domain.useCase.database


import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository
import javax.inject.Inject

class GetTopListFromDB @Inject constructor(private val repo: PictureRepository) {
    operator fun invoke() = repo.getTopListFromDB()
}