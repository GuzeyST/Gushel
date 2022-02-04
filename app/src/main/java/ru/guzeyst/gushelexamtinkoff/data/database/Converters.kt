package ru.guzeyst.gushelexamtinkoff.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.guzeyst.gushelexamtinkoff.data.database.entity.TypeChapter

class Converters {
    @TypeConverter
    fun fromTypeChapter(typeChapter: TypeChapter): String = Gson().toJson(typeChapter)


    @TypeConverter
    fun toTypeChapter(type: String): TypeChapter = Gson().fromJson(type, TypeChapter::class.java)
}