package com.example.pokeapi.presentation.pokeapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokeapi.PokeapiApplication
import com.example.pokeapi.data.pokeapi.PokeapiRepositoryImpl
import com.example.pokeapi.databinding.PokeapiFragmentBinding
import com.example.pokeapi.domain.pokeapi.PokeapiUseCase
import com.example.pokeapi.domain.pokeapi.model.PokemonListResult
import com.example.pokeapi.framework.pokeapi.PokeapiDataSourceImpl
import com.example.pokeapi.presentation.pokeapi.adapter.PokeapiAdapter
import com.example.pokeapi.presentation.utils.SpaceDecorator
import com.example.pokeapi.presentation.utils.getViewModel
import com.example.pokeapi.presentation.utils.showToast
import com.example.pokeapi.presentation.utils.toDp

class PokeapiFragment : Fragment() {

    private val pokeapiContext by lazy { requireContext() }
    private val serviceLocator by lazy { PokeapiApplication.serviceLocator }
    private val pokeapiDataSource by lazy {
        PokeapiDataSourceImpl(serviceLocator.providePokeapiService())
    }
    private val pokeapiRepository by lazy { PokeapiRepositoryImpl(pokeapiDataSource) }
    private val pokeapiUseCase by lazy { PokeapiUseCase(pokeapiRepository) }
    private val viewModel by lazy { getViewModel { PokeapiViewModel(pokeapiUseCase) } }

    private val adapter = PokeapiAdapter(::onPokemonClicked)

    private var _binding: PokeapiFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = PokeapiFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUi()
    }

    private fun setupUi(){
        with(binding) {
            rvPokemons.apply {
                adapter = this@PokeapiFragment.adapter
                addItemDecoration(SpaceDecorator(8.toDp()))
            }
        }

        viewModel.getPokemonList()
    }

    private fun setupObservers() {
        with(viewModel) {
            onError.observe(viewLifecycleOwner) {
                pokeapiContext.showToast(it)
            }
            onDisplayLoader.observe(viewLifecycleOwner) {
                toggleLoader(it)
            }
            onPokemonListFetch.observe(viewLifecycleOwner) {
                submitList(it)
            }
        }
    }

    private fun onPokemonClicked(name: String){
        findNavController().navigate(PokeapiFragmentDirections.actionPokeapiFragmentToPokemonInfoFragment(name))
    }

    private fun submitList(list: List<PokemonListResult>) {
        adapter.submitList(list)
    }

    private fun toggleLoader(status: Boolean) {
        if(status) {
            binding.pbLoader.visibility = View.VISIBLE
        } else {
            binding.pbLoader.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}