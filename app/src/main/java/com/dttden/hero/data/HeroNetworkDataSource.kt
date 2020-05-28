package com.dttden.hero.data

import com.dttden.hero.api.HeroApi

class HeroNetworkDataSource {
    suspend fun loadHeroes(year: Int) = HeroApi.loadHeroes(year)
}
