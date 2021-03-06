package com.example.pokemon.api

import com.example.pokemon.models.entity.AbilityDetailed
import com.example.pokemon.models.entity.AllPokemonResponse
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.models.entity.Species
import com.example.pokemon.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("pokemon")
    suspend fun getAllPokemonList(
        @Query("limit") limit: Int = Constants.QUERY_PAGE_SIZE,
        @Query("offset") offset: Int = 0,
    ): AllPokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String): PokemonResponse

    @GET("ability/{id}")
    suspend fun getAbility(@Path("id") id: String): AbilityDetailed

    @GET("pokemon-species/{id}")
    suspend fun getSpecies(@Path("id") id: String): Species

    companion object {
        fun create(): PokemonAPI {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            return retrofit.create(PokemonAPI::class.java)
        }
    }
}