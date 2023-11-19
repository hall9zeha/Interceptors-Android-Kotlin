package com.barryzeha.interceptorsapp.domain.usecases

import com.barryzeha.interceptorsapp.data.model.PokemonResult
import com.barryzeha.interceptorsapp.domain.model.PokemonEntity


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 17/11/23.
 * Copyright (c)  All rights reserved.
 **/

interface GetPokemonsUseCase {
      suspend fun getPokemonList(perPage:Int):List<PokemonEntity>
}