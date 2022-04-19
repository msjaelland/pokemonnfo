package com.example.pokemon.presentation.details

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.pokemon.R
import com.example.pokemon.models.entity.Effect
import com.example.pokemon.presentation.epoxy.KotlinEpoxyHolder
import com.example.pokemon.util.loadImage

@EpoxyModelClass
abstract class PokemonStatModel : EpoxyModelWithHolder<PokemonStatModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_poke_stat

    @EpoxyAttribute
    var icon: Drawable? = null

    @EpoxyAttribute
    var name: String? = null

    @EpoxyAttribute
    var value: String? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        with(holder) {
            pokeStatTv.text = name
            pokeStatValueTv.text = value
            if (icon != null) {
                statIconIv.visibility = View.VISIBLE
                statIconIv.setImageDrawable(icon)
            } else {
                statIconIv.visibility = View.GONE
            }
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val pokeStatTv: TextView by bind(R.id.pokeStatsTv)
        val pokeStatValueTv: TextView by bind(R.id.pokeStatsValueTv)
        val statIconIv: ImageView by bind(R.id.statIconIv)
    }
}

@EpoxyModelClass
abstract class PokemonAbilityModel : EpoxyModelWithHolder<PokemonAbilityModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_poke_ability

    @EpoxyAttribute
    var effect: Effect? = null

    @EpoxyAttribute
    var name: String? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        with(holder) {
            pokeAbilityNameTv.text = name
            pokeAbilityFullDescriptionTv.text = effect?.effect
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val pokeAbilityNameTv: TextView by bind(R.id.pokeAbilityNameTv)
        val pokeAbilityFullDescriptionTv: TextView by bind(R.id.pokeAbilityFullDescriptionTv)
    }
}

@EpoxyModelClass
abstract class SectionHeaderModel : EpoxyModelWithHolder<SectionHeaderModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_section_header

    @EpoxyAttribute
    var text: String? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        with(holder) {
            sectionHeaderTv.text = text
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val sectionHeaderTv: TextView by bind(R.id.sectionHeaderTv)

    }
}

@EpoxyModelClass
abstract class DetailsHeaderImageModel :
    EpoxyModelWithHolder<DetailsHeaderImageModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_poke_details_header

    @EpoxyAttribute
    var imageUrl: String? = null

    @EpoxyAttribute
    var backgroundColour: Int? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        with(holder) {
            pokeDetailsHeaderIv.loadImage(imageUrl)
            backgroundColour?.let {
                pokeDetailHeaderBackgroundCl.setBackgroundColor(it)
            }
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val pokeDetailHeaderBackgroundCl: ConstraintLayout by bind(R.id.pokeDetailHeaderBackGroundCl)
        val pokeDetailsHeaderIv: ImageView by bind(R.id.pokeDetailsHeaderIv)

    }
}
