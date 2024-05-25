package com.example.project.previews.index

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project.entities.PokemonEntity
import com.example.project.presentation.index.IndexScreen
import com.example.project.presentation.index.components.PokemonCard


@Preview(showBackground = true)
@Composable
private fun IndexPreview() {
    IndexScreen().Content()
}

@Preview(showBackground = true)
@Composable
private fun PokemonCardPreview() {
    Column(modifier = Modifier.padding(20.dp).size(150.dp)) {
        PokemonCard(PokemonEntity(1, "bulbasaur", ""))
    }
}

