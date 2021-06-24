/* MIT License
 *
 * Copyright (c) 2021 Florian Trecul
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/

package com.floriantrecul.pokedex.ui.data.mapper

import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.data.model.PokemonAbout
import com.floriantrecul.pokedex.ui.data.model.PokemonDetailsUiModel

class PokemonDetailsUiMapper(
    private val pokemonAboutUiMapper: PokemonAboutUiMapper,
    private val pokemonMovesUiMapper: PokemonMovesUiMapper,
    private val pokemonBaseStatsUiMapper: PokemonBaseStatsUiMapper
) {

    fun mapToDomainModelPokemonDetailUi(pokemon: Pokemon): PokemonDetailsUiModel =
        PokemonDetailsUiModel(
            id = pokemon.id,
            name = pokemon.name,
            imageUrl = pokemon.imageUrl,
            pokemonAbout = pokemonAboutUiMapper.mapToDomainModelPokemonAboutUi(
                PokemonAbout(
                    pokemon.description,
                    pokemon.height,
                    pokemon.weight,
                    pokemon.abilities
                )
            ),
            moves = pokemon.moves.map { pokemonMovesUiMapper.mapToDomainModelPokemonMovesUi(it) },
            stats = pokemon.stats.map { pokemonBaseStatsUiMapper.mapToDomainModelPokemonBaseStatsUi(it) },
            types = pokemon.types
        )
}