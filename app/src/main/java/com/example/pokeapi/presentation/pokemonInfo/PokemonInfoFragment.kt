package com.example.pokeapi.presentation.pokemonInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokeapi.PokeapiApplication
import com.example.pokeapi.R
import com.example.pokeapi.data.pokeapi.PokeapiRepositoryImpl
import com.example.pokeapi.databinding.PokemonInfoFragmentBinding
import com.example.pokeapi.domain.pokeapi.PokeapiUseCase
import com.example.pokeapi.framework.pokeapi.PokeapiDataSourceImpl
import com.example.pokeapi.presentation.utils.getViewModel
import com.example.pokeapi.presentation.utils.showToast

class PokemonInfoFragment : Fragment() {

    private val pokeapiContext by lazy { requireContext() }
    private val serviceLocator by lazy { PokeapiApplication.serviceLocator }
    private val pokeapiDataSource by lazy {
        PokeapiDataSourceImpl(serviceLocator.providePokeapiService())
    }
    private val pokeapiRepository by lazy { PokeapiRepositoryImpl(pokeapiDataSource) }
    private val pokeapiUseCase by lazy { PokeapiUseCase(pokeapiRepository) }
    private val viewModel by lazy { getViewModel { PokemonInfoViewModel(pokeapiUseCase) } }

    private val args: PokemonInfoFragmentArgs by navArgs()

    private var _binding: PokemonInfoFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = PokemonInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUi()
    }

    private fun setupUi(){
        viewModel.getPokemonInfo(args.name)
    }

    private fun setupObservers() {
        with(viewModel) {
            onError.observe(viewLifecycleOwner) {
                pokeapiContext.showToast(it)
            }
            onDisplayLoader.observe(viewLifecycleOwner) {
                toggleLoader(it)
            }
            onPokemonInfoFetch.observe(viewLifecycleOwner) {
                with(binding){
                    tvName.text = pokeapiContext.getString(R.string.text_name,it.name)
                    tvExp.text = pokeapiContext.getString(R.string.text_exp,it.base_experience.toString())
                    tvHeight.text = pokeapiContext.getString(R.string.text_height,it.height.toString())
                    tvWeight.text = pokeapiContext.getString(R.string.text_weight,it.weight.toString())
                }
            }
        }
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