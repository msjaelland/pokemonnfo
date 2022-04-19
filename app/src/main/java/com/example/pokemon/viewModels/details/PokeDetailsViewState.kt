package com.example.pokemon.viewModels.details

import com.example.pokemon.models.entity.AbilityDetailed
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.models.entity.Species

data class PokeDetailsViewState(
    val loading: Boolean = true,
    val pokemon: PokemonResponse? = null,
    val detailedAbilities: List<AbilityDetailed> = listOf(),
    val species: Species? = null
)