package com.dttden.hero.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dttden.hero.ui.viewmodel.HeroesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HeroesViewModel::class)
    abstract fun bindTeamsViewModel(heroesViewModel: HeroesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
