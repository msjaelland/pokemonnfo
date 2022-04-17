package com.example.pokemon.presentation.allPokemon

import android.content.res.Resources
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.pokemon.R
import com.example.pokemon.models.entity.PokemonResponse
import com.example.pokemon.presentation.epoxy.KotlinEpoxyHolder
import com.example.pokemon.util.addZeroPadding
import com.example.pokemon.util.loadImage

@EpoxyModelClass
abstract class PokemonName : EpoxyModelWithHolder<PokemonName.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_poke_details

    @EpoxyAttribute
    var name: String? = null

    @EpoxyAttribute
    var onClick: () -> Unit = {}

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        with(holder) {
            name?.let {
                pokeNameTv.text = it
                pokeNameTv.setOnClickListener {
                    onClick()
                }
            }
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val pokeNameTv: TextView by bind(R.id.pokeDetailsName)
    }
}

@EpoxyModelClass
abstract class PokemonListEntryModel : EpoxyModelWithHolder<PokemonListEntryModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_poke_list_entry

    @EpoxyAttribute
    var onClick: () -> Unit = {}

    @EpoxyAttribute
    var pokemon: PokemonResponse? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        with(holder) {
            pokeNameTv.text = pokemon?.name
            pokemonIdTv.text = "#${pokemon?.id?.addZeroPadding()}"
            pokemonSpriteIv.loadImage(pokemon?.sprites?.front_default)

            pokemonListEntryOuterCl.setOnClickListener {
                onClick()
            }
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val pokeNameTv: TextView by bind(R.id.pokemonNameTv)
        val pokemonIdTv: TextView by bind(R.id.pokemonIdTv)
        val pokemonSpriteIv: ImageView by bind(R.id.pokemonSpriteIv)
        val pokemonListEntryOuterCl: ConstraintLayout by bind(R.id.pokemonListEntryOuterCl)
    }
}

@EpoxyModelClass
abstract class PaginationButtonModel : EpoxyModelWithHolder<PaginationButtonModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_pagination_buttons

    @EpoxyAttribute
    var resources: Resources? = null

    @EpoxyAttribute
    var onPreviousClick: () -> Unit = {}

    @EpoxyAttribute
    var onNextClick: () -> Unit = {}

    @EpoxyAttribute
    var hasNext: Boolean = false

    @EpoxyAttribute
    var hasPrevious: Boolean = false

    @EpoxyAttribute
    var totalResults: Int? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        with(holder) {
            previousBtn.isVisible = hasPrevious
            nextButton.isVisible = hasNext
            
            previousBtn.text = resources?.getString(R.string.Previous)
            previousBtn.setOnClickListener {
                onPreviousClick.invoke()
            }
            nextButton.text = resources?.getString(R.string.Next)
            nextButton.setOnClickListener {
                onNextClick.invoke()
            }
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val previousBtn: Button by bind(R.id.paginationPreviousBtn)
        val nextButton: Button by bind(R.id.paginationNextBtn)
    }
}
