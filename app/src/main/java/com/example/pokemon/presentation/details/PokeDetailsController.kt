package com.example.pokemon.presentation.details

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.R
import com.example.pokemon.models.entity.AbilityDetailed
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.models.entity.Species
import com.example.pokemon.presentation.common.loading
import com.example.pokemon.presentation.common.textEntry
import com.example.pokemon.util.insertHeightSpacing

class PokeDetailsController(
    val resources: Resources
) : EpoxyController() {

    var pokemon: PokemonResponse? = null
    var isLoading: Boolean = true
    var abilities: List<AbilityDetailed> = listOf()
    var species: Species? = null

    override fun buildModels() {
        when (isLoading) {
            true -> loading {
                id("loading")
            }
            false -> {
                buildHeaderImage(pokemon, resources)
                buildGeneralInfo(pokemon, species, resources)
                buildSpecies(pokemon, species, resources)
                buildStats(resources)
                buildAbilities(resources)
            }
        }
    }

    private fun buildSpecies(pokemon: PokemonResponse?, species: Species?, resources: Resources) {
        sectionHeader {
            id("speciesSectionHeader")
            text(resources.getString(R.string.species))
        }

        buildStatEntry(
            "${pokemon?.name}:habitatId",
            resources.getString(R.string.species_habitat),
            species?.habitat?.name
        )
        buildStatEntry(
            "${pokemon?.name}:baseHappinessId",
            resources.getString(R.string.species_base_happiness),
            species?.base_happiness.toString()
        )
        buildStatEntry(
            "${pokemon?.name}:captureRateId",
            resources.getString(R.string.species_capture_rate),
            species?.capture_rate.toString()
        )
        buildStatEntry(
            "${pokemon?.name}:growthRateId",
            resources.getString(R.string.species_growth_rate),
            species?.growth_rate?.name
        )
        insertHeightSpacing("speciesHeightSpacing")
    }

    private fun buildGeneralInfo(
        pokemon: PokemonResponse?,
        species: Species?,
        resources: Resources
    ) {
        sectionHeader {
            id("generalInfoSectionHeader")
            text(resources.getString(R.string.section_general_info))
        }
        textEntry {
            id("flavorTextEntry")
            text(species?.getEnglishFlavorText())
        }
        buildStatEntry(
            "${pokemon?.name}:baseExperience",
            resources.getString(R.string.base_experience),
            pokemon?.base_experience.toString()
        )
        buildStatEntry(
            "${pokemon?.name}:generalInfoWeight",
            resources.getString(R.string.stat_weight),
            pokemon?.weight.toString()
        )
        buildStatEntry(
            "${pokemon?.name}:generalInfoHeight",
            resources.getString(R.string.stat_height),
            pokemon?.height.toString()
        )
        insertHeightSpacing("genericInfoHeightSpacing")
    }

    private fun buildAbilities(resources: Resources) {
        sectionHeader {
            id("abilitySectionHeader")
            text(resources.getString(R.string.section_abilities))
        }
        abilities.forEach { ability ->
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
            text(resources.getString(R.string.section_base_stats))
        }

        var statTotal = 0
        pokemon?.stats?.forEach { stat ->
            val name = pokemon?.name
            buildStatEntry(
                "pokemon:$name stat:${stat.stat?.name}",
                stat.stat?.name, stat.base_stat.toString(), stat.getIcon(resources)
            )
            stat.base_stat?.let {
                statTotal += it
            }
        }

        buildStatEntry(
            "${pokemon?.name}:totalStats",
            resources.getString(R.string.stat_total),
            statTotal.toString(),
            ResourcesCompat.getDrawable(resources, R.drawable.ic_sum_icon, null)
        )
        insertHeightSpacing("statsHeightSpacing")
    }

    private fun buildHeaderImage(pokemon: PokemonResponse?, resources: Resources) {
        pokemon?.let {
            detailsHeaderImage {
                id("detailsHeaderImage")
                imageUrl(pokemon.sprites?.front_default)
                backgroundColour(it.types?.first()?.getColour(resources))
            }
        }
    }

    private fun buildStatEntry(id: String, name: String?, value: String?, icon: Drawable? = null) {
        pokemonStat {
            id(id)
            name(name)
            value(value)
            icon(icon)
        }
    }
}