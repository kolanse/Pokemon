package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.adapter.OnPokemonListClick
import com.example.pokemon.adapter.PokemonRecyclerAdapter
import com.example.pokemon.data.PokemonData
import com.example.pokemon.data.PokemonList
import com.example.pokemon.data.ResultsData
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.overview.OverviewModel
import com.example.pokemon.utils.pokemonApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import java.util.EnumSet.of
import javax.security.auth.callback.Callback
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity(), OnPokemonListClick {


    lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      mainBinding =   DataBindingUtil.setContentView(this,R.layout.activity_main)

        var recycler_pokemon = mainBinding.recyclerPokemon




      // get the viewModel
        val viewModel = ViewModelProvider(this).get(OverviewModel::class.java)

        /**
         * Observes the livedata of the pokemon results
         */
        viewModel.getPokemonData().observe(this, Observer {
         recycler_pokemon.adapter = PokemonRecyclerAdapter(it.results, this)
            recycler_pokemon.layoutManager = LinearLayoutManager(this)
            recycler_pokemon.setHasFixedSize(true)



        })









    }

    /**
     * In charge of handling the click made on each recycler item
     */
    override fun onItemClick(item: ResultsData, position: Int) {





        var intent = Intent(this, PokemonDetails::class.java).apply {
            putExtra("NUM", "${getNumber(item.url)}")
        }


        startActivity(intent)


    }

    /**
     * Get the number of selected pokemon
     */
    fun getNumber(url:String): String{
        var init = url.split('/')
        var number = init[init.size - 2]

        return number
    }



}