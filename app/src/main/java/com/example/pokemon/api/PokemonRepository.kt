package com.example.pokemon.api

import com.example.pokemon.models.entity.AbilityDetailed
import com.example.pokemon.models.entity.AllPokemonResponse
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.models.entity.Species

class PokemonRepository {
    private val pokemonApi: PokemonAPI = PokemonAPI.create()

    suspend fun getPokemonDetails(id: String): PokemonResponse {
        return pokemonApi.getPokemon(id)
    }

    suspend fun getAllPokemon(limit: Int, offset: Int): AllPokemonResponse {
        return pokemonApi.getAllPokemonList(limit, offset)
    }

    suspend fun getAbility(id: String): AbilityDetailed {
        return pokemonApi.getAbility(id)
    }

    suspend fun getSpecies(id: String): Species {
        return pokemonApi.getSpecies(id)
    }
}