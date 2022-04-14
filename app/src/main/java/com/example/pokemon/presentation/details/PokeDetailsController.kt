package com.example.pokemon.presentation.details

import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.presentation.allPokemon.pokemonName
import com.example.pokemon.presentation.common.loading
import com.example.pokemon.presentation.common.smallSprite

class PokeDetailsController : EpoxyController() {

    var pokemon: PokemonResponse? = null
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
        pokemon?.let {
            pokemonName {
                id("name")
                name(it.name)
            }
            pokemonName {
                id("height")
                name(it.height.toString())
            }
            pokemonName {
                id("weight")
                name(it.weight.toString())
            }
            smallSprite {
                id("image")
                url(it.sprites?.front_default)
            }
        }

    }
}