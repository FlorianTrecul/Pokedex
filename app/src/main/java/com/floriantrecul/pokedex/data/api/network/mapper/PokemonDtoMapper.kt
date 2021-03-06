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

package com.floriantrecul.pokedex.data.api.network.mapper

import com.floriantrecul.pokedex.BuildConfig
import com.floriantrecul.pokedex.data.api.domain.DomainMapper
import com.floriantrecul.pokedex.data.api.network.model.PokemonDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonNamedApiDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonSpeciesDto
import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.data.model.PokemonItem
import com.floriantrecul.pokedex.util.extension.*

class PokemonDtoMapper : DomainMapper<PokemonDto, Any> {

    override fun mapToDomainModelPokemonItem(domain: PokemonNamedApiDto, secondDomain: PokemonDto): PokemonItem = PokemonItem(
        id = domain.url.extractPokemonId(),
        name = domain.name,
        imageUrl = "${BuildConfig.POKEMON_IMAGE_URL}${domain.url.extractPokemonId()}.png",
        types = secondDomain.types.map { it.type.name.toPokemonType() }
    )

    override fun mapToDomainModelPokemon(
        domain: PokemonDto,
        secondDomain: PokemonSpeciesDto
    ): Pokemon = Pokemon(
        id = domain.id,
        name = domain.name,
        description = secondDomain.flavorTextEntries.getPokemonDescription(),
        imageUrl = "${BuildConfig.POKEMON_IMAGE_URL}${domain.id}.png",
        height = domain.height,
        weight = domain.weight,
        abilities = domain.abilities.map { it.toPokemonAbility() },
        moves = domain.moves.map { it.toPokemonMove() },
        stats = domain.stats.map { it.toPokemonStat() },
        types = domain.types.map { it.type.name.toPokemonType() }
    )

    override fun mapToDomainModelCount(count: Int): Int = count

}