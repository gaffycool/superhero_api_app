package com.dttden.api

import com.dttden.api.model.Heroes
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesApi {
    @GET("{year}.json")
    suspend fun loadHeroes(@Path("year") year: Int): Heroes
}
