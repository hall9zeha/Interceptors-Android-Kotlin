package com.barryzeha.interceptorsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.barryzeha.interceptorsapp.R
import com.barryzeha.interceptorsapp.databinding.ActivityMainBinding
import com.barryzeha.interceptorsapp.ui.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var bind:ActivityMainBinding
    private lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setUpVieModel()
        viewModel.fetchPokemons(20)
    }

    private fun setUpVieModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.pokemonList.observe(this){
            Log.e("POKEMON_RESPONSE", it.toString() )
        }
    }
}