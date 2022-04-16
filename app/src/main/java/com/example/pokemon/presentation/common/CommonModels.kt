package com.example.pokemon.presentation.common

import android.widget.Button
import android.widget.ImageView
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
abstract class LoadMoreButtonModel : EpoxyModelWithHolder<LoadMoreButtonModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_load_more_button

    @EpoxyAttribute
    var loadMoreText: String? = null

    override fun bind(holder: LoadMoreButtonModel.ViewHolder) {
        super.bind(holder)

        loadMoreText?.let {
            holder.loadMoreButton.text = it
        }
    }

    inner class ViewHolder : KotlinEpoxyHolder() {
        val loadMoreButton: Button by bind(R.id.loadMoreBtn)
    }
}
