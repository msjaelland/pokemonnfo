package com.example.pokemon.models.entity

data class PokemonResponse(
    val name: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val order: Int? = null,
    val sprites: Sprites? = null,
    val stats: List<Stat>? = null,
    val abilities: List<Ability>? = null,
    val types: List<Type>? = null,
    val id: Int? = null
)