package com.example.pokemon.models

data class AllPokemonResponse(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonListEntryResponse>? = null
)