package com.example.project.util

object Constants {
   const val OFFICIAL_ARTWORK_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
    fun getOfficialArtworkUrl(id: Int): String = "$OFFICIAL_ARTWORK_URL$id.png"
}