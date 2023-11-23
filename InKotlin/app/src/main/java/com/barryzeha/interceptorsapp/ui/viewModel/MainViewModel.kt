package com.barryzeha.interceptorsapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barryzeha.interceptorsapp.common.utils.SingleMutableLiveData
import com.barryzeha.interceptorsapp.data.network.getApi
import com.barryzeha.interceptorsapp.data.repository.RepositoryImpl
import com.barryzeha.interceptorsapp.domain.model.PokemonData
import com.barryzeha.interceptorsapp.domain.model.PokemonResponse
import com.barryzeha.interceptorsapp.domain.usecases.GetPokemonsUseCase
import com.barryzeha.interceptorsapp.domain.usecases.GetPokemonsUseCaseImpl
import kotlinx.coroutines.launch

/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

class MainViewModel : ViewModel() {
    
    private val getPokemonUseCase: GetPokemonsUseCase = GetPokemonsUseCaseImpl(RepositoryImpl(getApi()))

    private var _pokemonResponse: MutableLiveData<PokemonData> = MutableLiveData()
    val pokemonResponse: LiveData<PokemonData> = _pokemonResponse

    private var _msgUnsuccessful:SingleMutableLiveData<String> = SingleMutableLiveData()
    val msgUnsuccessful:SingleMutableLiveData<String> = _msgUnsuccessful

    fun fetchPokemonData(perPage: Int) {
        viewModelScope.launch {

            when (val response = getPokemonUseCase.fetchPokemonData(perPage)) {
                is PokemonResponse.ResponseSuccess -> {
                    _pokemonResponse.postValue(response.pokemonResponse)
                }
                is PokemonResponse.ResponseError -> {
                    _msgUnsuccessful.postValue(response.errorMsg)
                    Log.e("RESPONSE_ERROR", response.errorMsg)
                }
            }

        }
    }


}