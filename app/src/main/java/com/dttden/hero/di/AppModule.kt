package com.dttden.hero.di

import android.content.Context
import com.dttden.hero.HeroApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: HeroApplication): Context {
        return application.applicationContext
    }
}
