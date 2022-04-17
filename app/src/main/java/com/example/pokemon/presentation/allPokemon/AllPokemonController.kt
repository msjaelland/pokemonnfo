package com.example.pokemon.presentation.allPokemon

import android.content.res.Resources
import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.presentation.common.loading

class AllPokemonController(
    val resources: Resources
) : EpoxyController() {

    var pokemonList: List<PokemonResponse> = listOf()
    var isLoading: Boolean = true
    var onEntryClick: (String) -> Unit = {}
    var onNextClick: () -> Unit = {}
    var onPreviousClick: () -> Unit = {}
    var hasNext: Boolean = true
    var hasPrevious: Boolean = false


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
        val onPreviousClick = onPreviousClick
        val onNextClick = onNextClick
        val resources = resources

        pokemonList.forEach { pokemon ->
            pokemonListEntry {
                id("pokedetails${pokemon.name}")
                pokemon(pokemon)
                onClick {
                    pokemon?.name?.let { name ->
                        onEntryClick.invoke(name)
                    }
                }
                resources(resources)
            }
        }

        val hasNext = hasNext
        val hasPrevious = hasPrevious

        paginationButton {
            id("paginationButtons")
            hasNext(hasNext)
            hasPrevious(hasPrevious)
            resources(resources)
            onPreviousClick(onPreviousClick)
            onNextClick(onNextClick)
        }
    }
}