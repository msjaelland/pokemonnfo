package com.example.pokemon.models.entity

data class AllPokemonResponse(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<NameAndUrl>? = null,
)