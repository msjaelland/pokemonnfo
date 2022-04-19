package com.example.pokemon.presentation.common

import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.pokemon.R
import com.example.pokemon.presentation.epoxy.KotlinEpoxyHolder
import com.example.pokemon.util.loadImage

@EpoxyModelClass
abstract class LoadingModel : EpoxyModelWithHolder<LoadingModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_loading_view

    inner class ViewHolder : KotlinEpoxyHolder() {
    }
}

@EpoxyModelClass
abstract class HeightSpacingModel : EpoxyModelWithHolder<HeightSpacingModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_height_spacing

    inner class ViewHolder : KotlinEpoxyHolder() {
    }
}

@EpoxyModelClass
abstract class SmallSpriteModel : EpoxyModelWithHolder<SmallSpriteModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_sprite_small

    @EpoxyAttribute
    var url: String? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        with(holder) {
            url?.let {
                pokemonIv.loadImage(it)
            }
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val pokemonIv: ImageView by bind(R.id.spriteIv)
    }
}

@EpoxyModelClass
abstract class TextEntryModel : EpoxyModelWithHolder<TextEntryModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_text_entry

    @EpoxyAttribute
    var text: String? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        with(holder) {
            text?.let {
                entryTv.text = it
            }
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val entryTv: TextView by bind(R.id.textEntryTv)
    }
}