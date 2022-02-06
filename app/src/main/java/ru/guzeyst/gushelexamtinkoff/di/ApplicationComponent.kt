package ru.guzeyst.gushelexamtinkoff.di

import android.app.Activity
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.hotFragment.HotFragment
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.latestFragment.LatestFragment
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.randomFragment.RandomFragment
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.topFragment.TopFragment

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: HotFragment)
    fun inject(fragment: TopFragment)
    fun inject(fragment: LatestFragment)
    fun inject(fragment: RandomFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}