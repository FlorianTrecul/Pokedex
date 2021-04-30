package com.floriantrecul.pokedex.data.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpeciesDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "color")
    val color: PokemonNamedApiDto,
    @Json(name = "egg_groups")
    val eggGroups: List<PokemonNamedApiDto>,
    @Json(name = "shape")
    val shape: PokemonNamedApiDto,
    @Json(name = "flavor_text_entries")
    val flavorTextEntries: List<PokemonFlavorTextDto>
)

@JsonClass(generateAdapter = true)
data class PokemonFlavorTextDto (
    @Json(name = "flavor_text")
    val flavorText: String,
    @Json(name = "language")
    val language: PokemonNamedApiDto,
)

