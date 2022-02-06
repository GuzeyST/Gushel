package ru.guzeyst.gushelexamtinkoff.domain.useCase.database


import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository

class GetTopListFromDB(private val repo: PictureRepository) {
    operator fun invoke() = repo.getTopListFromDB()
}