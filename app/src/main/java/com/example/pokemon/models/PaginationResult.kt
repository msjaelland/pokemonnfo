package com.example.pokemon.models

import com.example.pokemon.util.Constants

class PaginationResult(
    var nextLimit: Int? = Constants.QUERY_PAGE_SIZE,
    var nextOffset: Int? = 0,
    var previousLimit: Int? = null,
    var previousOffSet: Int? = null,
    var totalResults: Int? = null
)