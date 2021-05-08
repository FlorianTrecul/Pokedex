package com.floriantrecul.pokedex.data.api.domain

import com.floriantrecul.pokedex.data.api.network.model.PokemonDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonNamedApiDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonSpeciesDto

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModelPokemonItem(domain: PokemonNamedApiDto, secondDomain: PokemonDto): DomainModel

    fun mapToDomainModelPokemon(domain: PokemonDto, secondDomain: PokemonSpeciesDto): DomainModel

    fun mapToDomainModelCount(count: Int): DomainModel
}