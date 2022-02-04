package ru.guzeyst.gushelexamtinkoff.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.guzeyst.gushelexamtinkoff.data.database.entity.*

@Dao
interface PicturesDao {

    //Entity picture item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picture: PictureItem)

    @Query("SELECT * FROM pictures")
    fun getPicturesList(): LiveData<List<PictureItem>>


}