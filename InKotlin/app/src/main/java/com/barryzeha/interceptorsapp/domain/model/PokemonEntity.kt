package com.barryzeha.interceptorsapp.domain.model

import com.barryzeha.interceptorsapp.common.getNumbersOfLastUrl
import com.barryzeha.interceptorsapp.data.model.PokemonResult


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 19/11/23.
 * Copyright (c)  All rights reserved.
 **/

data class PokemonEntity(val name:String="", val imageUrl:String="")
fun PokemonResult.toDomain():PokemonEntity= with(this) {
 val pokemonId = getNumbersOfLastUrl(url)[0]
 return PokemonEntity(
   name=name,
  imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"

 )
}