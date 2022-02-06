package ru.guzeyst.gushelexamtinkoff.domain.useCase.database


import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository

class GetLatestListFromDB(private val repo: PictureRepository) {
    operator fun invoke() = repo.getLatestListFromDB()
}