package com.example.pokemon.models.entity

data class PokemonResponse(
    val name: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val order: Int? = null,
    val sprites: Sprites? = null,
    val stats: List<Stats>? = null,
    val abilities: List<Ability>? = null
)