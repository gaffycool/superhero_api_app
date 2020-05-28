package com.dttden.api.di

import com.dttden.api.HeroClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    @Component.Builder
    interface Builder {
        fun apiModule(apiModule: ApiModule): Builder
        fun build(): ApiComponent
    }

    fun inject(client: HeroClient)
}

