package com.example.pokemon.presentation.details

import android.content.res.Resources
import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.R
import com.example.pokemon.models.entity.NameAndUrl
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.models.entity.Stat
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

        var statTotal = 0
        pokemon?.stats?.forEach { stat ->
            val name = pokemon?.name
            pokemonStats {
                id("pokemon:$name stat:${stat.stat?.name}")
                resources(resources)
                stat(stat)
            }
            stat?.base_stat?.let {
                statTotal += it
            }
        }
        val totalStat = Stat(base_stat = statTotal, stat = NameAndUrl(name = Stat.Total))
        pokemonStats {
            id("totalStats")
            resources(resources)
            stat(totalStat)
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