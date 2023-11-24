package com.barryzeha.interceptorsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barryzeha.interceptorsapp.R
import com.barryzeha.interceptorsapp.common.loadUrl
import com.barryzeha.interceptorsapp.databinding.PokemonItemBinding
import com.barryzeha.interceptorsapp.domain.model.PokemonEntity


/**
 * Project InterceptorsApp
 * Created by Barry Zea H. on 19/11/23.
 * Copyright (c)  All rights reserved.
 **/

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: MutableList<PokemonEntity> = arrayListOf()
    private var isLoading:Boolean = false
    private val VIEW_TYPE_NORMAL = 1
    private val VIEW_TYPE_LOADING = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_item, parent, false)
        val itemLoading = LayoutInflater.from(context).inflate(R.layout.item_loading,parent,false)
        return when(viewType){
            VIEW_TYPE_NORMAL->ViewHolder(itemView)
            else->ViewHolderLoading(itemLoading)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            VIEW_TYPE_NORMAL->{
                (holder as ViewHolder).onBind(itemList[position])
            }
        }

    }

    fun addAll(items: List<PokemonEntity>) {
        items.forEach { add(it) }
    }

    private fun add(item: PokemonEntity) {
        if (!itemList.contains(item)) {
            itemList.add(item)
            notifyItemInserted(itemList.size - 1)
        }
    }

    override fun getItemCount() = itemList.size
    override fun getItemViewType(position: Int): Int {
        return if (position == itemList.size - 1 && isLoading) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_NORMAL
        }
    }
    fun addLoadingItem(){
        isLoading = true
        add(PokemonEntity())
        notifyItemInserted(itemList.size -1)
    }
    fun removeLoadingItem(){
        isLoading=false
        val position = itemList.size - 1
        val item = itemList[position]
        item?.let{
            itemList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bind = PokemonItemBinding.bind(itemView)
        fun onBind(pokemon: PokemonEntity) = with(bind) {
            ivPokemon.loadUrl(pokemon.imageUrl)
            tvName.text=pokemon.name
        }
    }
    inner class ViewHolderLoading(itemView: View ):RecyclerView.ViewHolder(itemView){}
}