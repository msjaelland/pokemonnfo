package com.example.pokemon.viewModels.allPokemon

import androidx.lifecycle.ViewModel
import com.example.pokemon.api.PokemonAPI
import com.example.pokemon.models.entity.AllPokemonResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllPokemonViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(AllPokemonViewState())
    val allPokemonState: StateFlow<AllPokemonViewState> = _stateFlow

    private val pokemonAPI = PokemonAPI.create()

    fun loadMorePokemon() {
        _stateFlow.value = _stateFlow.value.copy(
            loading = true
        )

        pokemonAPI.getAllPokemon().enqueue(object : Callback<AllPokemonResponse?> {
            override fun onResponse(
                call: Call<AllPokemonResponse?>,
                response: Response<AllPokemonResponse?>
            ) {
                response.body()?.let {
                    it.results?.forEach { entry ->
                        println(entry.name)
                    }
                    _stateFlow.value = _stateFlow.value.copy(
                        loading = false,
                        hasMoreResults = true,
                        allPokemon = it
                    )
                }
            }

            override fun onFailure(call: Call<AllPokemonResponse?>, t: Throwable) {
                _stateFlow.value = _stateFlow.value.copy(
                    loading = false
                )
            }
        })
    }
}