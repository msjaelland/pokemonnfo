package com.example.pokemon.presentation.allPokemon

import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.models.entity.AllPokemonResponse
import com.example.pokemon.presentation.common.loading

class AllPokemonController : EpoxyController() {

    var pokemons: AllPokemonResponse? = null
    var isLoading: Boolean = true

    override fun buildModels() {
        when (isLoading) {
            true -> loading {
                id("loading")
            }
            false -> buildList()
        }
    }

    private fun buildList(){
        pokemons?.let {
            it.results?.let { results ->
                results.forEach { entry ->
                    pokemonName {
                        id("pokedetails${entry.name}")
                        name(entry.name)
                    }
                }
            }
        }
    }

}