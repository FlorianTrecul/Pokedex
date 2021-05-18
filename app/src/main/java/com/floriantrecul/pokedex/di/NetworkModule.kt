package com.floriantrecul.pokedex.di

import com.floriantrecul.pokedex.data.api.network.mapper.PokemonDtoMapper
import com.floriantrecul.pokedex.data.api.network.service.PokedexService
import com.floriantrecul.pokedex.ui.data.mapper.PokemonDetailsUiMapper
import com.floriantrecul.pokedex.ui.data.mapper.PokemonItemUiMapper
import com.floriantrecul.pokedex.util.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePokemonDtoMapper(): PokemonDtoMapper = PokemonDtoMapper()

    @Singleton
    @Provides
    fun providePokemonUiMapper(): PokemonItemUiMapper = PokemonItemUiMapper()

    @Singleton
    @Provides
    fun providePokemonDetailsUiMapper(): PokemonDetailsUiMapper = PokemonDetailsUiMapper()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun providePokedexService(retrofit: Retrofit): PokedexService =
        retrofit.create(PokedexService::class.java)

}