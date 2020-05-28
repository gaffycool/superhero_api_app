package com.dttden.hero.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.time.Year

@Dao
interface HeroDAO {
    @Query("SELECT * FROM HERO WHERE YEAR = :year ORDER BY score DESC")
    fun findHeroes(year: Int): LiveData<List<HeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeroes(heroEntities: List<HeroEntity>)

    @Query("DELETE FROM HERO WHERE YEAR =:year")
    fun deleteHeroes(year: Int)
}
