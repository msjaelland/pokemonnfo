package com.example.pokemon.presentation.allPokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokeDetailsBinding
import com.example.pokemon.presentation.details.PokeDetailsFragment
import com.example.pokemon.viewModels.allPokemon.AllPokemonViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AllPokemonFragment : Fragment(R.layout.fragment_all_pokemon) {

    private var _binding: FragmentPokeDetailsBinding? = null
    private val binding get() = _binding!!

    private val controller by lazy { AllPokemonController(resources) }
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
        setupController()

        observeData()
        if (!viewModel.hasData()) {
            viewModel.loadNextPokemon()
        }
        controller.requestModelBuild()
    }

    private fun setupController() {
        controller.onEntryClick = ::navigateToDetails
        controller.onPreviousClick = viewModel::loadPreviousPokemon
        controller.onNextClick = viewModel::loadNextPokemon
    }

    private fun navigateToDetails(id: String) {
        var bundle = bundleOf(PokeDetailsFragment.PokemonIdArg to id)
        findNavController().navigate(R.id.action_List_to_Details, bundle)
    }

    private fun observeData() {
        viewModel.allPokemonState
            .onEach {
                controller.isLoading = it.loading
                controller.pokemonList = it.pokemonList
                controller.hasNext = it.hasNext
                controller.hasPrevious = it.hasPrevious
                controller.requestModelBuild()
            }
            .launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}