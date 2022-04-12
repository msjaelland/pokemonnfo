package com.example.pokemon.presentation.common

import com.example.pokemon.presentation.epoxy.KotlinEpoxyHolder
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.pokemon.R

@EpoxyModelClass
abstract class LoadingModel : EpoxyModelWithHolder<LoadingModel.ViewHolder>() {
    override fun getDefaultLayout(): Int = R.layout.item_loading_view

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
    }

    inner class ViewHolder : KotlinEpoxyHolder() {

    }

}
