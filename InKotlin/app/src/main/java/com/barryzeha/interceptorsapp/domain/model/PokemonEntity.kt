package com.barryzeha.interceptorsapp.domain.model

import com.barryzeha.interceptorsapp.common.getNumbersOfLastUrl
import com.barryzeha.interceptorsapp.data.model.Pokemon
import com.barryzeha.interceptorsapp.data.model.PokemonResult


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 19/11/23.
 * Copyright (c)  All rights reserved.
 **/

data class PokemonResponse(val totalItems:Int=0,val pokemons:List<PokemonEntity> = arrayListOf())
data class PokemonEntity(val name:String="", val imageUrl:String="")
fun Pokemon.toDomain():PokemonResponse = with(this){
    return PokemonResponse(
        totalItems = count,
        pokemons = result.map { pokemon->pokemon.toDomain() }
    )
}
fun PokemonResult.toDomain():PokemonEntity= with(this) {
 val pokemonId = getNumbersOfLastUrl(url)[0]
 return PokemonEntity(
     name=name,
     imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"

 )

}
