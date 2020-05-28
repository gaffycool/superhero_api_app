package com.dttden.hero.data

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.dttden.hero.database.HeroDataBase
import com.dttden.hero.database.HeroEntity

class HeroLocalDataSource(private val database: HeroDataBase) {
    fun findHeroes(year: Int): LiveData<List<HeroEntity>> {
        return database.heroDAO().findHeroes(year)
    }

    fun insertHeroes(heroes: List<HeroEntity>) {
        AsyncTask.execute {
            database.heroDAO().insertHeroes(heroes)
        }
    }

    fun deleteHeroes(year: Int) {
        AsyncTask.execute {
            database.heroDAO().deleteHeroes(year)
        }
    }
}
