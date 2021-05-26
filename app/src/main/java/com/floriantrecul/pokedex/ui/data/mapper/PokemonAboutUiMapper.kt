package com.floriantrecul.pokedex.ui.data.mapper

import com.floriantrecul.pokedex.data.model.PokemonAbout
import com.floriantrecul.pokedex.ui.data.model.PokemonAboutUiModel

class PokemonAboutUiMapper {

    fun mapToDomainModelPokemonAboutUi(pokemonAbout: PokemonAbout): PokemonAboutUiModel = PokemonAboutUiModel(
        description = pokemonAbout.description,
        height = pokemonAbout.height,
        weight = pokemonAbout.weight,
        abilities = pokemonAbout.abilities
    )

}