package ru.guzeyst.gushelexamtinkoff.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.hotFragment.HotViewModel
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.latestFragment.LatestViewModel
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.randomFragment.RandomViewModel
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.topFragment.TopViewModel

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HotViewModel::class)
    fun bindHotViewModel(viewModel: HotViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(TopViewModel::class)
    fun bindTopViewModel(viewModel: TopViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(LatestViewModel::class)
    fun bindLatestViewModel(viewModel: LatestViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(RandomViewModel::class)
    fun bindRandomViewModel(viewModel: RandomViewModel): ViewModel
}