package com.example.pokemon.presentation.details

import android.content.res.Resources
import android.widget.ImageView
import com.example.pokemon.presentation.epoxy.KotlinEpoxyHolder
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.pokemon.R
import com.example.pokemon.models.entity.Stat

@EpoxyModelClass
abstract class PokemonStatsModel : EpoxyModelWithHolder<PokemonStatsModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_poke_stat

    @EpoxyAttribute
    var stat: Stat? = null

    @EpoxyAttribute
    var resources: Resources? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        with(holder) {
            pokeStatTv.text = stat?.stat?.name
            pokeStatValueTv.text = stat?.base_stat.toString()
            resources?.let {
                val icon = stat?.getIcon(it)
                statIconIv.setImageDrawable(icon)
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
