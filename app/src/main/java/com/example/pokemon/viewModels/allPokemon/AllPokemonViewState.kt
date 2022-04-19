package com.example.pokemon.viewModels.allPokemon

import com.example.pokemon.models.entity.PokemonResponse

data class AllPokemonViewState(
    val loading: Boolean = true,
    val pokemonList: List<PokemonResponse> = listOf(),

    val hasNext: Boolean = true,
    val hasPrevious: Boolean = false
)