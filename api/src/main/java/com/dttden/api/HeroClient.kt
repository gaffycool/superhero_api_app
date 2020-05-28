package com.dttden.api

import com.dttden.api.di.ApiModule
import com.dttden.api.di.DaggerApiComponent
import javax.inject.Inject

class HeroClient {

    @Inject
    lateinit var heroesApi: HeroesApi

    @Inject
    constructor() {
        setupHeroClient()
    }

    private fun setupHeroClient() {
        DaggerApiComponent.builder()
            .apiModule(ApiModule("https://bitbucket.org/dttden/mobile-coding-challenge/raw/2ee8bd47703c62c5d217d9fb9e0306922a34e581/"))
            .build()
            .inject(this)
    }

    companion object {
        var instance = HeroClient()
    }
}

