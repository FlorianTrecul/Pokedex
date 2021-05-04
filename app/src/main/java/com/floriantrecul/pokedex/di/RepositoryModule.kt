package com.floriantrecul.pokedex.di

import com.floriantrecul.pokedex.data.api.network.mapper.PokemonDtoMapper
import com.floriantrecul.pokedex.data.api.network.service.PokedexService
import com.floriantrecul.pokedex.data.repository.PokemonRepository
import com.floriantrecul.pokedex.data.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providePokemonRepository(
        pokedexService: PokedexService,
        pokemonDtoMapper: PokemonDtoMapper
    ): PokemonRepository = PokemonRepositoryImpl(pokedexService, pokemonDtoMapper)
}