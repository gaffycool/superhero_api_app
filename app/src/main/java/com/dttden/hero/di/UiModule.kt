package com.dttden.hero.di

import com.dttden.hero.ui.heroes.HeroActivity
import com.dttden.hero.ui.heroes.HeroesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {
    @ContributesAndroidInjector
    abstract fun bindHeroActivity(): HeroActivity

    @ContributesAndroidInjector(modules = [ResourceModule::class, ViewModelModule::class])
    abstract fun bindHeroesFragment(): HeroesFragment
}
