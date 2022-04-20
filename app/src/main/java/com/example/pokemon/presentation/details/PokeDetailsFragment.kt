package com.example.pokemon.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokeDetailsBinding
import com.example.pokemon.viewModels.details.PokeDetailsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokeDetailsFragment : Fragment(R.layout.fragment_poke_details) {

    private var _binding: FragmentPokeDetailsBinding? = null
    private val binding get() = _binding!!

    private val controller by lazy { PokeDetailsController(resources) }
    private val viewModel: PokeDetailsViewModel by viewModel()

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
        arguments?.getString(PokemonIdArg)?.let {
            setFragmentTitle(it)
            if (!viewModel.hasData()){
                viewModel.getPokemon(it)
            }
        }
        controller.requestModelBuild()
    }

    private fun observeData() {
        viewModel.pokemonDetails
            .onEach {
                controller.isLoading = it.loading
                controller.pokemon = it.pokemon
                controller.abilities = it.detailedAbilities
                controller.species = it.species
                controller.requestModelBuild()
            }
            .launchIn(lifecycleScope)
    }

    private fun setFragmentTitle(title: String?) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val PokemonIdArg = "pokemonIdArg"
    }
}