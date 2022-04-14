package com.example.pokemon.viewModels.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PokeDetailsViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(PokeDetailsViewState())
    val allPokemonState: StateFlow<PokeDetailsViewState> = _stateFlow
}