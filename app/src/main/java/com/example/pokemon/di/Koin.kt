package com.example.pokemon.di

import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.util.UriUtils
import com.example.pokemon.viewModels.allPokemon.AllPokemonViewModel
import com.example.pokemon.viewModels.details.PokeDetailsViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(repos, viewModels, utils)
    }
}

val repos = module {
    single { PokemonRepository() }
}

val utils = module {
    single { UriUtils() }
}

val viewModels = module {
    factory { PokeDetailsViewModel(get(), get()) }
    factory { AllPokemonViewModel(get(), get()) }
}



