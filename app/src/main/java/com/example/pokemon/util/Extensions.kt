package com.example.pokemon.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(imageUrl: String?) {
    Picasso.with(context).load(imageUrl).into(this)
}