package com.dttden.hero.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dttden.api.model.Hero
import com.dttden.api.model.Heroes
import com.dttden.hero.database.HeroEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class HeroesRepository(
    private val heroNetworkDataSource: HeroNetworkDataSource,
    private val heroLocalDataSource: HeroLocalDataSource
) {

    fun getHeroes(year: Int): LiveData<List<Hero>> {
        val heroEntities = heroLocalDataSource.findHeroes(year)
        return Transformations.map(heroEntities) { entities -> toVOs(entities) }
    }

    suspend fun loadHeroes(year: Int) {
        withContext(Dispatchers.IO) {
            try {
                val heroes = heroNetworkDataSource.loadHeroes(year)
                heroLocalDataSource.deleteHeroes(year)
                heroLocalDataSource.insertHeroes(toEntities(year, heroes))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun toVOs(heroes: List<HeroEntity>): List<Hero> {
        val heroesVO = ArrayList<Hero>()
        for (hero in heroes) {
            heroesVO.add(HeroEntityMapper.toVO(hero))
        }
        return heroesVO
    }

    private fun toEntities(year: Int, heroes: Heroes): List<HeroEntity> {
        val heroesVO = ArrayList<HeroEntity>()
        for (hero in heroes.heroes) {
            heroesVO.add(HeroEntityMapper.toEntity(year, hero))
        }
        return heroesVO
    }
}
