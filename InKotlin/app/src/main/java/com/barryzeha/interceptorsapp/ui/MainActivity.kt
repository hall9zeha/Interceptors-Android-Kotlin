package com.barryzeha.interceptorsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.barryzeha.interceptorsapp.common.getNumbersOfLastUrl
import com.barryzeha.interceptorsapp.databinding.ActivityMainBinding
import com.barryzeha.interceptorsapp.ui.adapter.RecyclerViewAdapter
import com.barryzeha.interceptorsapp.ui.viewModel.MainViewModel
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var bind:ActivityMainBinding
    private lateinit var viewModel:MainViewModel
    private lateinit var adapter:RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setUpRecyclerView()
        setUpVieModel()
        viewModel.fetchPokemons(20)
    }
    private fun setUpRecyclerView(){
        adapter = RecyclerViewAdapter()
        bind.rvMain.apply {
            layoutManager = GridLayoutManager(this@MainActivity,2)
            setHasFixedSize(true)
            adapter=this@MainActivity.adapter
        }

    }
    private fun setUpVieModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.pokemonList.observe(this){
            adapter.addAll(it)
            Log.e("POKEMON_RESPONSE", it.toString() )

        }
    }

}