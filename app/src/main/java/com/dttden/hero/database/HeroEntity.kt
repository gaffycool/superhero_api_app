package com.dttden.hero.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HERO")
data class HeroEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "picture") val picture: String,
    @ColumnInfo(name = "score") val score: Int,
    @ColumnInfo(name = "year") val year: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}
