package com.floriantrecul.pokedex.util.extension

import com.floriantrecul.pokedex.data.api.network.model.PokemonAbilityDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonFlavorTextDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonMoveDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonSpeciesDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonStatDto
import com.floriantrecul.pokedex.data.model.PokemonAbility
import com.floriantrecul.pokedex.data.model.PokemonMove
import com.floriantrecul.pokedex.data.model.PokemonStat
import com.floriantrecul.pokedex.data.model.PokemonType

fun PokemonAbilityDto.toPokemonAbility(): PokemonAbility = PokemonAbility(
    name = this.ability.name
)

fun PokemonMoveDto.toPokemonMove(): PokemonMove = PokemonMove(
    name = this.move.name,
)

fun PokemonStatDto.toPokemonStat(): PokemonStat = PokemonStat(
    name = this.stat.name,
    value = this.baseStat
)

fun String.toPokemonType(): PokemonType =
    PokemonType.values().find { it.value == this } ?: PokemonType.UNKNOWN

fun List<PokemonFlavorTextDto>.getPokemonDescription(): String =
    find { it.language.name == "en" }!!.flavorText

fun PokemonSpeciesDto.getMainColor(): String = this.color.name

private val pokemonIdPattern = "/\\d+/".toRegex()

fun String.extractPokemonId(): Int {
    val pokemonId = pokemonIdPattern.find(this)!!.value
    return pokemonId.drop(1).dropLast(1).toInt()
}