package com.example.pokemon.models

import com.example.pokemon.util.Constants

class PaginationResult(
    val nextLimit: Int = Constants.QUERY_PAGE_SIZE,
    val nextOffset: Int = 0,
    val previousLimit: Int? = null,
    val previousOffSet: Int? = null,
    val totalResults: Int? = null
)