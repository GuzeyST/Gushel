package ru.guzeyst.gushelexamtinkoff.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "pictures",
    primaryKeys = ["id", "type_chapter"],
    indices = [
        Index("type_chapter")
    ]
)
data class PictureItem(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "type_chapter") val typeChapter: TypeChapter,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "gifURL") val gifURL: String
)