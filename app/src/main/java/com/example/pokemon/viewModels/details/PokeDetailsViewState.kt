package com.example.pokemon.viewModels.details

import com.example.pokemon.models.entity.PokemonResponse

data class PokeDetailsViewState(
    val loading: Boolean = true,
    val pokemon: PokemonResponse? = null
)