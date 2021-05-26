package com.floriantrecul.pokedex.di

import com.floriantrecul.pokedex.data.api.network.mapper.PokemonDtoMapper
import com.floriantrecul.pokedex.ui.data.mapper.PokemonAboutUiMapper
import com.floriantrecul.pokedex.ui.data.mapper.PokemonBaseStatsUiMapper
import com.floriantrecul.pokedex.ui.data.mapper.PokemonDetailsUiMapper
import com.floriantrecul.pokedex.ui.data.mapper.PokemonItemUiMapper
import com.floriantrecul.pokedex.ui.data.mapper.PokemonMovesUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Singleton
    @Provides
    fun providePokemonDtoMapper(): PokemonDtoMapper = PokemonDtoMapper()

    @Singleton
    @Provides
    fun providePokemonUiMapper(): PokemonItemUiMapper = PokemonItemUiMapper()

    @Singleton
    @Provides
    fun providePokemonDetailsUiMapper(
        pokemonAboutUiMapper: PokemonAboutUiMapper,
        pokemonMovesUiMapper: PokemonMovesUiMapper,
        pokemonBaseStatsUiMapper: PokemonBaseStatsUiMapper
    ): PokemonDetailsUiMapper =
        PokemonDetailsUiMapper(pokemonAboutUiMapper, pokemonMovesUiMapper, pokemonBaseStatsUiMapper)

    @Singleton
    @Provides
    fun providePokemonBaseAboutMapper(): PokemonAboutUiMapper = PokemonAboutUiMapper()

    @Singleton
    @Provides
    fun providePokemonBaseStatsUiMapper(): PokemonBaseStatsUiMapper = PokemonBaseStatsUiMapper()

    @Singleton
    @Provides
    fun providePokemonMovesUiMapper(): PokemonMovesUiMapper = PokemonMovesUiMapper()
}
