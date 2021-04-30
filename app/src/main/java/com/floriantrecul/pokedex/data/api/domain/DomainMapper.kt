package com.floriantrecul.pokedex.data.api.domain

import com.floriantrecul.pokedex.data.api.network.model.PokemonDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonNamedApiDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonSpeciesDto
import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.data.model.PokemonItem

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModelPokemonItem(domain: PokemonNamedApiDto): PokemonItem

    fun mapToDomainModelPokemon(domain: PokemonDto, secondDomain: PokemonSpeciesDto): Pokemon

}