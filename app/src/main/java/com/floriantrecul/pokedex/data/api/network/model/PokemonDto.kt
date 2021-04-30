package com.floriantrecul.pokedex.data.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "weight")
    val weight: Int,
    @Json(name = "abilities")
    val abilities: List<PokemonAbilityDto>,
    @Json(name = "moves")
    val moves: List<PokemonMoveDto>,
    @Json(name = "stats")
    val stats: List<PokemonStatDto>,
    @Json(name = "types")
    val types: List<PokemonTypeDto>
)

@JsonClass(generateAdapter = true)
data class PokemonAbilityDto(
    @Json(name = "ability")
    val ability: PokemonNamedApiDto
)

@JsonClass(generateAdapter = true)
data class PokemonMoveDto(
    @Json(name = "move")
    val move: PokemonNamedApiDto
)

@JsonClass(generateAdapter = true)
data class PokemonStatDto(
    @Json(name = "base_stat")
    val baseStat: Int,
    @Json(name = "stat")
    val stat: PokemonNamedApiDto
)

@JsonClass(generateAdapter = true)
data class PokemonTypeDto(
    @Json(name = "type")
    val type: PokemonNamedApiDto
)

