package com.example.pokemon.presentation.allPokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokeDetailsBinding
import com.example.pokemon.viewModels.allPokemon.AllPokemonViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AllPokemonFragment : Fragment(R.layout.fragment_all_pokemon) {

    private var _binding: FragmentPokeDetailsBinding? = null
    private val binding get() = _binding!!

    private val controller by lazy { AllPokemonController() }
    private val viewModel: AllPokemonViewModel by viewModels()

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

        binding.pokeDetailsRV.setController(controller)
        observeData()
        viewModel.loadMorePokemon()
        controller.requestModelBuild()
    }

    private fun observeData() {
        viewModel.allPokemonState
            .onEach {
                controller.isLoading = it.loading
                controller.pokemons = it.allPokemon
                controller.requestModelBuild()
            }
            .launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}