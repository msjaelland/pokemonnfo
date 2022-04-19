package com.example.pokemon.models.entity

data class Species(
    val capture_rate: Int? = null,
    val base_happiness: Int? = null,
    val flavor_text_entries: List<FlavorText> = listOf(),
    val habitat: NameAndUrl? = null,
    val growth_rate: NameAndUrl? = null
) {
    fun getEnglishFlavorText(): String? {
        val englishFlavor = flavor_text_entries.firstOrNull { x -> x.language?.name == "en" }
        return englishFlavor?.flavor_text?.replace("\n", " ")
    }
}