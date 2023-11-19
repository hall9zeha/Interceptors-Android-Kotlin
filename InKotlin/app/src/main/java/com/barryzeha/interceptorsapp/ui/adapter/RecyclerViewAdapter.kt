package com.barryzeha.interceptorsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barryzeha.interceptorsapp.R
import com.barryzeha.interceptorsapp.common.loadUrl
import com.barryzeha.interceptorsapp.data.model.Pokemon
import com.barryzeha.interceptorsapp.data.model.PokemonResult
import com.barryzeha.interceptorsapp.databinding.ItemLayoutBinding
import com.barryzeha.interceptorsapp.domain.model.PokemonEntity


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 19/11/23.
 * Copyright (c)  All rights reserved.
 **/

class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
 private var itemList:MutableList<PokemonEntity> = arrayListOf()
 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
   val context = parent.context
   val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout,parent, false)
   return ViewHolder(itemView)
 }

 override fun onBindViewHolder(holder: ViewHolder, position: Int) {
   holder.onBind(itemList[position])
 }
 fun addAll(items:List<PokemonEntity>){
  items.forEach { add(it) }
 }
 private fun add(item:PokemonEntity){
  if(!itemList.contains(item)){
   itemList.add(item)
   notifyItemInserted(itemList.size-1)
  }
 }
 override fun getItemCount() = itemList.size

 inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
     val bind = ItemLayoutBinding.bind(itemView)
   fun onBind(pokemon:PokemonEntity)= with(bind){

        ivPokemon.loadUrl(pokemon.imageUrl)
   }
 }
}