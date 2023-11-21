package com.barryzeha.interceptorsapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barryzeha.interceptorsapp.domain.model.PokemonResponse
import com.barryzeha.interceptorsapp.domain.usecases.GetPokemonsUseCase
import com.barryzeha.interceptorsapp.domain.usecases.GetPokemonsUseCaseImpl
import kotlinx.coroutines.launch

/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

class MainViewModel:ViewModel() {
  private val getPokemonUseCase:GetPokemonsUseCase = GetPokemonsUseCaseImpl()

 private var _pokemonResponse:MutableLiveData<PokemonResponse> = MutableLiveData()
 val pokemonResponse: LiveData<PokemonResponse> = _pokemonResponse

 fun fetchPokemonData(perPage:Int){
     viewModelScope.launch {
         _pokemonResponse.value = getPokemonUseCase.fetchPokemonData(perPage)
     }
 }


}