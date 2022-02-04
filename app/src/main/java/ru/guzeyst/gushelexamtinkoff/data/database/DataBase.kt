package ru.guzeyst.gushelexamtinkoff.data.database

import android.content.Context
import androidx.room.*
import ru.guzeyst.gushelexamtinkoff.data.database.entity.PictureItem

@Database(
    entities = [
        PictureItem::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DataBase: RoomDatabase() {

    companion object{
        private var db: DataBase? = null
        private const val DB_NAME = "Pictures.db"
        private val LOCK = Any()

        fun getInstance(context: Context): DataBase{
            db?.let { return it }
            synchronized(LOCK) {
                db?.let { return it }
                val dbInstance = Room.databaseBuilder(
                    context,
                    DataBase::class.java,
                    DB_NAME
                ).build()
                db = dbInstance
                return dbInstance
            }
        }
    }

    abstract fun pictureDao(): PicturesDao
}