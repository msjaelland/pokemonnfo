package com.example.pokemon.presentation.details

import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.models.AllPokemonResponse
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
        pokeDetails {
            id("firstEntry")
            name("Epoxy working!")
        }

        pokemons?.let {
            it.results?.let { results ->
                results.forEach { entry ->
                    pokeDetails {
                        id("pokedetails${entry.name}")
                        name(entry.name)
                    }
                }
            }
        }


    }
}