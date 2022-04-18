package com.example.pokemon.presentation.details

import android.content.res.Resources
import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.R
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.presentation.allPokemon.pokemonName
import com.example.pokemon.presentation.common.loading
import com.example.pokemon.presentation.common.smallSprite

class PokeDetailsController(
    val resources: Resources
) : EpoxyController() {

    var pokemon: PokemonResponse? = null
    var isLoading: Boolean = true

    override fun buildModels() {
        when (isLoading) {
            true -> loading {
                id("loading")
            }
            false -> {
                buildDetails()
                buildStats(resources)
            }
        }
    }

    private fun buildStats(resources: Resources) {
        sectionHeader {
            id("statsSectionHeader")
            text(resources.getString(R.string.base_stats))
        }

        pokemon?.stats?.forEach { stat ->
            val name = pokemon?.name
            pokemonStats {
                id("pokemon:$name stat:${stat.stat?.name}")
                resources(resources)
                stat(stat)
            }
        }
    }

    private fun buildDetails() {
        pokemon?.let {

            smallSprite {
                id("image")
                url(it.sprites?.front_default)
            }
        }

    }
}