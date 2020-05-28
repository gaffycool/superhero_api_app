package com.dttden.api.model

import com.google.gson.annotations.SerializedName

data class Heroes(@SerializedName("Heroes") val heroes: List<Hero>)
