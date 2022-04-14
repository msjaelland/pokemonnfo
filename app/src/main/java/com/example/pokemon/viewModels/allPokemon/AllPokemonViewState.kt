package com.example.pokemon.viewModels.allPokemon

import com.example.pokemon.models.entity.AllPokemonResponse

data class AllPokemonViewState(
    val loading: Boolean = true,
    val hasMoreResults: Boolean = true,
    val allPokemon: AllPokemonResponse? = null
)