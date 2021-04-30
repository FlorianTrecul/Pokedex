package com.floriantrecul.pokedex.data.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonNamedApiDto(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)
