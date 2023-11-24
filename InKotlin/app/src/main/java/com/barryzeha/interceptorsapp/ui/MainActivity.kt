package com.barryzeha.interceptorsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barryzeha.interceptorsapp.common.utils.ViewModelFactory
import com.barryzeha.interceptorsapp.data.network.getApi
import com.barryzeha.interceptorsapp.data.repository.RepositoryImpl
import com.barryzeha.interceptorsapp.databinding.ActivityMainBinding
import com.barryzeha.interceptorsapp.domain.model.PokemonData
import com.barryzeha.interceptorsapp.domain.usecases.GetPokemonsUseCaseImpl
import com.barryzeha.interceptorsapp.ui.adapter.RecyclerViewAdapter
import com.barryzeha.interceptorsapp.ui.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var bind:ActivityMainBinding
    private lateinit var viewModel:MainViewModel
    private lateinit var adapter:RecyclerViewAdapter
    private lateinit var  scrollListener:RecyclerView.OnScrollListener
    private lateinit var layoutManager:GridLayoutManager
    private var isLoading = false
    private var isLastPage = false
    //por defecto la api devuelve 20 elementos por llamada, si queremos avanzar a una siguiente página debemos
    //ir sumando 20 a la cantidad  inicial  por cada llamada (o el número de elementos que hayamos configurado por llamada)
    private var perPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setUpRecyclerView()
        setUpVieModel()
        setUpPaginationRecyclerView()
        viewModel.fetchPokemonData(perPage)
    }
    private fun setUpRecyclerView(){
        adapter = RecyclerViewAdapter()
        layoutManager = GridLayoutManager(this@MainActivity,2)
        bind.rvMain.apply {
            layoutManager = this@MainActivity.layoutManager
            setHasFixedSize(true)
            adapter=this@MainActivity.adapter
        }

    }
    private fun setUpVieModel() {

        val factory = ViewModelFactory{MainViewModel(GetPokemonsUseCaseImpl(RepositoryImpl(getApi())))}
        //Inyectamos la dependencia en el viewModel a través de nuestro view model factory
        viewModel = ViewModelProvider(this,factory)[MainViewModel::class.java]

        viewModel.pokemonResponse.observe(this,::updateUi)
        viewModel.msgUnsuccessful.observe(this){msg->
            Snackbar.make(bind.root,msg!!,Snackbar.LENGTH_LONG).show()
        }
    }
    private fun setUpPaginationRecyclerView(){
        scrollListener = object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemCount = layoutManager.findFirstVisibleItemPosition()
                if(!isLoading && !isLastPage){
                    if((visibleItemCount + firstVisibleItemCount)>=totalItemCount - 2 && firstVisibleItemCount>=0){
                        loadMoreItems()
                    }
                }
            }
        }
        bind.rvMain.addOnScrollListener(scrollListener)
    }
    private fun loadMoreItems(){
        isLoading = true
        perPage +=20
        adapter.addLoadingItem()
        viewModel.fetchPokemonData(perPage)
    }
    private fun updateUi(response:PokemonData){
        response?.let{
            if(isLoading && it.pokemons.isNotEmpty())adapter.removeLoadingItem()
            isLastPage = (perPage >= response.totalItems )
            isLoading = false
            adapter.addAll(it.pokemons)
        }
    }
}