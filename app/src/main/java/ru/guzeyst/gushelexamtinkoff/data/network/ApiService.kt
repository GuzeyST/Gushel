package ru.guzeyst.gushelexamtinkoff.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.guzeyst.gushelexamtinkoff.data.network.model.ResponseDto
import ru.guzeyst.gushelexamtinkoff.data.network.model.PictureDto

interface ApiService {

    @GET("random?json=true")
    suspend fun getPictureRandom(): PictureDto

    @GET("{chapter}/{page_number}?json=true")
    suspend fun getPicturesList(
        @Path("chapter")chapter: String,
        @Path("page_number")pageNumber: Int
    ): ResponseDto

    companion object{
        const val PATH_CHAPTER_LATEST = "latest"
        const val PATH_CHAPTER_HOT = "hot"
        const val PATH_CHAPTER_TOP = "top"
    }

}