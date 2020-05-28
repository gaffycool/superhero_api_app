package com.dttden.hero.api

import com.dttden.api.HeroClient
import com.dttden.api.model.Hero
import com.dttden.api.model.Heroes

object HeroApi {
    suspend fun loadHeroes(year: Int) = HeroClient.instance.heroesApi.loadHeroes(year)
}
