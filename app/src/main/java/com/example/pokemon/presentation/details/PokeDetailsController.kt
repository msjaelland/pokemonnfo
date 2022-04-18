package com.example.pokemon.presentation.details

import android.content.res.Resources
import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.R
import com.example.pokemon.models.entity.AbilityDetailed
import com.example.pokemon.models.entity.NameAndUrl
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.models.entity.Stat
import com.example.pokemon.presentation.allPokemon.pokemonName
import com.example.pokemon.presentation.common.heightSpacing
import com.example.pokemon.presentation.common.loading
import com.example.pokemon.presentation.common.smallSprite

class PokeDetailsController(
    val resources: Resources
) : EpoxyController() {

    var pokemon: PokemonResponse? = null
    var isLoading: Boolean = true
    var abilities: List<AbilityDetailed> = listOf()

    override fun buildModels() {
        when (isLoading) {
            true -> loading {
                id("loading")
            }
            false -> {
                buildDetails()
                buildStats(resources)
                buildAbilities(resources)
            }
        }
    }

    private fun buildAbilities(resources: Resources){
        sectionHeader {
            id("abilitySectionHeader")
            text(resources.getString(R.string.abilities))
        }
        abilities.forEach{ ability ->
            pokemonAbility {
                id("ability:${ability.name}")
                effect(ability.getEnglishEffect())
                name(ability.name)
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

        heightSpacing {
            id("statsHeightSpacing")
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