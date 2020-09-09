package com.example.pokemon.data

import com.squareup.moshi.Json

/**
 * Data class to handle the results gotten for the pokemon
 */
data class PokemonData (
    @field:Json(name = "results")
    val results: List<ResultsData>,


)

data class ResultsData(
    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "url")
    val url: String
)