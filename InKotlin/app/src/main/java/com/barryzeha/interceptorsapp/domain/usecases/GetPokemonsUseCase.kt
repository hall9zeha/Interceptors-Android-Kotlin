package com.barryzeha.interceptorsapp.domain.usecases

import com.barryzeha.interceptorsapp.domain.model.PokemonResponse


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

interface GetPokemonsUseCase {
      suspend fun fetchPokemonData(perPage:Int):PokemonResponse
}