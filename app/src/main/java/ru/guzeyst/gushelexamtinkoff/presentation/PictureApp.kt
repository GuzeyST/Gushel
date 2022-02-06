package ru.guzeyst.gushelexamtinkoff.presentation

import android.app.Application
import ru.guzeyst.gushelexamtinkoff.di.DaggerApplicationComponent

class PictureApp: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}