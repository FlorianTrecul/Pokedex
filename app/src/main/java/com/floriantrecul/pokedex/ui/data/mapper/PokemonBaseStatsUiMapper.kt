package com.floriantrecul.pokedex.ui.data.mapper

import com.floriantrecul.pokedex.data.model.PokemonStat
import com.floriantrecul.pokedex.ui.data.model.PokemonBaseStatsUiModel

class PokemonBaseStatsUiMapper {

    fun mapToDomainModelPokemonBaseStatsUi(pokemonStat: PokemonStat): PokemonBaseStatsUiModel = PokemonBaseStatsUiModel(
            name = pokemonStat.name,
            value = pokemonStat.value
        )
}