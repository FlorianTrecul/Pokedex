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

package com.floriantrecul.pokedex.data.repository

import com.floriantrecul.pokedex.data.api.network.mapper.PokemonDtoMapper
import com.floriantrecul.pokedex.data.api.network.service.PokedexService
import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.data.model.PokemonItem
import com.floriantrecul.pokedex.util.*
import com.floriantrecul.pokedex.util.extension.extractPokemonId
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokedexService: PokedexService,
    private val mapper: PokemonDtoMapper
) : PokemonRepository {

    override suspend fun getPokemons(limit: Int, offset: Int): Resource<List<PokemonItem>> {
        val response = try {
            pokedexService.getPokemons(limit, offset).results
        } catch (e: Exception) {
            return Resource.Error(e.toErrorMessage())
        }
        return Resource.Success(response.map {
            mapper.mapToDomainModelPokemonItem(
                it,
                pokedexService.getPokemon(it.url.extractPokemonId()),
            )
        })
    }

    override suspend fun getPokemon(pokemonId: Int): Pokemon {
        return mapper.mapToDomainModelPokemon(
            pokedexService.getPokemon(pokemonId),
            pokedexService.getPokemonSpecies(pokemonId)
        )
    }

    override suspend fun getCount(limit: Int, offset: Int): Int =
        mapper.mapToDomainModelCount(pokedexService.getPokemons(limit, offset).count)
}

