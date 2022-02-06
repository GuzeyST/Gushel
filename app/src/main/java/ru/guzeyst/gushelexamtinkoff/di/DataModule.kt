package ru.guzeyst.gushelexamtinkoff.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.guzeyst.gushelexamtinkoff.data.PictureRepositoryImpl
import ru.guzeyst.gushelexamtinkoff.data.database.DataBase
import ru.guzeyst.gushelexamtinkoff.data.database.PicturesDao
import ru.guzeyst.gushelexamtinkoff.domain.PictureRepository

@Module
interface DataModule {

    @Binds
    fun bindsPictureRepository(impl: PictureRepositoryImpl): PictureRepository

    companion object{
        @Provides
        fun providePicturesDao(
            application: Application
        ): PicturesDao{
            return DataBase.getInstance(application).pictureDao()
        }
    }
}