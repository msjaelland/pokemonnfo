package com.example.pokemon.presentation.allPokemon

import android.content.res.Resources
import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.models.entity.AllPokemonResponse
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.presentation.common.loading

class AllPokemonController(
    val resources: Resources
) : EpoxyController() {

    var pokemonResponse: AllPokemonResponse? = null
    var pokemonList: List<PokemonResponse> = listOf()
    var isLoading: Boolean = true
    var onEntryClick: (String) -> Unit = {}

    override fun buildModels() {
        when (isLoading) {
            true -> loading {
                id("loading")
            }
            false -> buildList()
        }
    }

    private fun buildList() {
        val onEntryClick = onEntryClick

        pokemonList.forEach { pokemon ->
            pokemonName {
                id("pokedetails${pokemon.name}")
                name(pokemon.name)
                onClick {
                    pokemon?.name?.let { name ->
                        onEntryClick.invoke(name)
                    }
                }
            }
        }
    }
}