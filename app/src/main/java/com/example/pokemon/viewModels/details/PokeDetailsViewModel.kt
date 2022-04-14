package com.example.pokemon.viewModels.details

import androidx.lifecycle.ViewModel
import com.example.pokemon.api.PokemonAPI
import com.example.pokemon.models.entity.PokemonResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeDetailsViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(PokeDetailsViewState())
    val pokemonDetails: StateFlow<PokeDetailsViewState> = _stateFlow

    fun getPokemon(id: String) {
        getSinglePokemon(id)
    }

    private fun getSinglePokemon(id: String) {
        val pokemonApi = PokemonAPI.create()

        _stateFlow.value = _stateFlow.value.copy(
            loading = true
        )

        pokemonApi.getSinglePokemon(id).enqueue(object : Callback<PokemonResponse?> {
            override fun onResponse(
                call: Call<PokemonResponse?>,
                response: Response<PokemonResponse?>
            ) {
                response.body()?.let {
                    _stateFlow.value = _stateFlow.value.copy(
                        loading = false,
                        pokemon = it
                    )
                }
            }

            override fun onFailure(call: Call<PokemonResponse?>, t: Throwable) {
                _stateFlow.value = _stateFlow.value.copy(
                    loading = false
                )
            }
        })
    }
}