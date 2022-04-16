package com.example.pokemon.presentation.allPokemon

import android.content.res.Resources
import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.R
import com.example.pokemon.models.entity.AllPokemonResponse
import com.example.pokemon.presentation.common.loadMoreButton
import com.example.pokemon.presentation.common.loading

class AllPokemonController(
    val resources: Resources
) : EpoxyController() {

    var pokemons: AllPokemonResponse? = null
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
        val resources = resources
        val onEntryClick = onEntryClick

        pokemons?.let {
            it.results?.let { results ->
                results.forEach { entry ->
                    pokemonName {
                        id("pokedetails${entry.name}")
                        name(entry.name)
                        onClick {
                            entry?.name?.let { name ->
                                onEntryClick.invoke(name)
                            }
                        }
                    }
                }
                loadMoreButton {
                    id("loadMoreButton")
                    loadMoreText(resources.getString(R.string.load_more_button_text))
                }
            }
        }
    }
}