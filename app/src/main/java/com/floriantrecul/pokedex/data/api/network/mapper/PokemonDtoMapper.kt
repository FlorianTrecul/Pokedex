package com.floriantrecul.pokedex.data.api.network.mapper

import com.floriantrecul.pokedex.data.api.domain.DomainMapper
import com.floriantrecul.pokedex.data.api.network.model.PokemonDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonNamedApiDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonSpeciesDto
import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.data.model.PokemonItem
import com.floriantrecul.pokedex.util.Constants.POKEMON_IMAGE_URL
import com.floriantrecul.pokedex.util.extension.extractPokemonId
import com.floriantrecul.pokedex.util.extension.getPokemonDescription
import com.floriantrecul.pokedex.util.extension.toPokemonAbility
import com.floriantrecul.pokedex.util.extension.toPokemonMove
import com.floriantrecul.pokedex.util.extension.toPokemonStat
import com.floriantrecul.pokedex.util.extension.toPokemonType

class PokemonDtoMapper : DomainMapper<PokemonDto, Any> {

    override fun mapToDomainModelPokemonItem(domain: PokemonNamedApiDto, secondDomain: PokemonDto): PokemonItem = PokemonItem(
        id = domain.url.extractPokemonId(),
        name = domain.name,
        imageUrl = "$POKEMON_IMAGE_URL${domain.url.extractPokemonId()}.png",
        types = secondDomain.types.map { it.type.name.toPokemonType() }
    )

    override fun mapToDomainModelPokemon(
        domain: PokemonDto,
        secondDomain: PokemonSpeciesDto
    ): Pokemon = Pokemon(
        id = domain.id,
        name = domain.name,
        description = secondDomain.flavorTextEntries.getPokemonDescription(),
        imageUrl = "$POKEMON_IMAGE_URL${domain.id}.png",
        height = domain.height,
        weight = domain.weight,
        abilities = domain.abilities.map { it.toPokemonAbility() },
        moves = domain.moves.map { it.toPokemonMove() },
        stats = domain.stats.map { it.toPokemonStat() },
        types = domain.types.map { it.type.name.toPokemonType() }
    )

    override fun mapToDomainModelCount(count: Int): Int = count

}