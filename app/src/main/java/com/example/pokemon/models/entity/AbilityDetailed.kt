package com.example.pokemon.models.entity

data class AbilityDetailed(
    val name: String? = null,
    val effect_entries: List<Effect>? = null
) {
    fun getEnglishEffect(): Effect? {
        return effect_entries?.firstOrNull { x -> x.language?.name == "en" }
    }
}