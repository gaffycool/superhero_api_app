package com.dttden.hero.di

import com.dttden.hero.HeroApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ViewModelModule::class, ResourceModule::class, UiModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: HeroApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: HeroApplication)
}
