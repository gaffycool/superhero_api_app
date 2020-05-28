package com.dttden.hero.di

import android.content.Context
import androidx.room.Room
import com.dttden.hero.data.HeroLocalDataSource
import com.dttden.hero.data.HeroNetworkDataSource
import com.dttden.hero.data.HeroesRepository
import com.dttden.hero.database.HeroDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ResourceModule {
    @Provides
    @Singleton
    fun provideHeroesNetworkDataSource(): HeroNetworkDataSource {
        return HeroNetworkDataSource()
    }

    @Provides
    @Singleton
    fun provideHeroesRepository(
        heroesNetworkDataSource: HeroNetworkDataSource,
        heroLocalDataSource: HeroLocalDataSource
    ): HeroesRepository {
        return HeroesRepository(heroesNetworkDataSource, heroLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideHeroesLocalDataSource(heroDatabase: HeroDataBase): HeroLocalDataSource {
        return HeroLocalDataSource(heroDatabase)
    }

    @Provides
    @Singleton
    fun provideHeroesDatabase(context: Context): HeroDataBase {
        return Room.databaseBuilder(context, HeroDataBase::class.java, "Heroes.db").build()
    }

}
