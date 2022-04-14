package com.example.pokemon.api

import com.example.pokemon.models.entity.AllPokemonResponse
import com.example.pokemon.util.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokemonAPI {

    @GET("pokemon")
    fun getAllPokemon(): Call<AllPokemonResponse>

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