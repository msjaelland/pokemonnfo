package com.example.pokemon.presentation.allPokemon

import com.example.pokemon.presentation.epoxy.KotlinEpoxyHolder
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.pokemon.R

@EpoxyModelClass
abstract class PokemonName : EpoxyModelWithHolder<PokemonName.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_poke_details

    @EpoxyAttribute
    var name: String? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        with(holder) {
            name?.let {
                pokeNameTv.text = it
            }
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val pokeNameTv: TextView by bind(R.id.pokeDetailsName)
    }

}
