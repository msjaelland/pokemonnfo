package com.example.pokemon.util

import android.net.Uri
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyController
import com.example.pokemon.presentation.common.heightSpacing
import com.squareup.picasso.Picasso

fun ImageView.loadImage(imageUrl: String?) {
    Picasso.with(context).load(imageUrl).into(this)
}

fun Int.addZeroPadding(): String {
    return if (this < 10) "00$this" else if (this < 100) "0$this" else this.toString()
}

fun String.extractLastUrlParam(): String? {
    return Uri.parse(this).lastPathSegment
}

fun String.extractQueryParameter(param: String): String? {
    return Uri.parse(this).getQueryParameter(param)
}

fun EpoxyController.insertHeightSpacing(id: String) {
    heightSpacing {
        id("id")
    }
}

inline fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}