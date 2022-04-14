package com.example.pokemon.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemon.R
import com.example.pokemon.api.PokemonAPI
import com.example.pokemon.databinding.FragmentPokeDetailsBinding
import com.example.pokemon.models.entity.AllPokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeDetailsFragment : Fragment(R.layout.fragment_poke_details) {

    private var _binding: FragmentPokeDetailsBinding? = null
    private val binding get() = _binding!!

    private val controller by lazy { PokeDetailsController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testGetAllPokemons()

        binding.pokeDetailsRV.setController(controller)
        controller.requestModelBuild()
    }

    private fun testGetAllPokemons() {
        val pokemonAPI = PokemonAPI.create().getAllPokemon()

        pokemonAPI.enqueue(object : Callback<AllPokemonResponse?> {
            override fun onResponse(
                call: Call<AllPokemonResponse?>,
                response: Response<AllPokemonResponse?>
            ) {
                response.body()?.let {
                    it.results?.forEach {entry ->
                        println(entry.name)
                    }
                    controller.pokemons = it
                    controller.isLoading = false
                    controller.requestModelBuild()
                }
            }

            override fun onFailure(call: Call<AllPokemonResponse?>, t: Throwable) {

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}