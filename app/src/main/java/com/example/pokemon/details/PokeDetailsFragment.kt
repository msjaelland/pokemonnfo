package com.example.pokemon.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokeDetailsBinding

class PokeDetailsFragment: Fragment(R.layout.fragment_poke_details) {

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

        binding.pokeDetailsRV.setController(controller)
        controller.requestModelBuild()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}