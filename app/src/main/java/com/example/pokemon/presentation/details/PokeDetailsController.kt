package com.example.pokemon.presentation.details

import com.airbnb.epoxy.EpoxyController

class PokeDetailsController : EpoxyController() {
    override fun buildModels() {
        buildDetails()
    }

    private fun buildDetails(){
        pokeDetails {
            id("pokeDetails")
            name("Test name is working!")
        }
    }
}