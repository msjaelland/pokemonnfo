package com.example.pokemon.presentation.details

import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.models.entity.AllPokemonResponse
import com.example.pokemon.presentation.common.loading

class PokeDetailsController : EpoxyController() {

    var pokemons: AllPokemonResponse? = null
    var isLoading: Boolean = true

    override fun buildModels() {
        when (isLoading) {
            true -> loading {
                id("loading")
            }
            false -> buildDetails()
        }
    }

    private fun buildDetails() {
    }
}