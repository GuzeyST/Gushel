package ru.guzeyst.gushelexamtinkoff.data.network.model

import com.google.gson.annotations.SerializedName

data class ListPictureDto(
    @SerializedName("result")
    val pictures: List<PictureDto>,
    @SerializedName("totalCount")
    val totalCount: Int
)