package com.dttden.hero.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HeroEntity::class], version = 1)
abstract class HeroDataBase : RoomDatabase() {
    abstract fun heroDAO(): HeroDAO
}
