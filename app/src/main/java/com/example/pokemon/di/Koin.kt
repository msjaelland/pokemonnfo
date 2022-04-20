package com.example.pokemon.di

import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.viewModels.allPokemon.AllPokemonViewModel
import com.example.pokemon.viewModels.details.PokeDetailsViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(repos, viewModels)
    }
}

val repos = module {
    single { PokemonRepository() }
}

val viewModels = module {
    factory { PokeDetailsViewModel(get()) }
    factory { AllPokemonViewModel(get()) }
}



