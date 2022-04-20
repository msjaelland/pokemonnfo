package com.example.pokemon.util

import android.net.Uri

class UriUtils {

    fun extractLastUrlParam(url: String?): String? {
        return Uri.parse(url).lastPathSegment
    }

    fun extractQueryParameter(url: String?, param: String?): String? {
        return Uri.parse(url).getQueryParameter(param)
    }

}