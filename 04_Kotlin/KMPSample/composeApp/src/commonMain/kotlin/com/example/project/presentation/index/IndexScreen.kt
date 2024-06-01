package com.example.project.presentation.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.project.entities.PokemonEntity
import com.example.project.presentation.index.IndexScreenState.Success
import com.example.project.presentation.index.components.PokemonCard
import kmpproject.composeapp.generated.resources.Res
import kmpproject.composeapp.generated.resources.get_pokemon
import kmpproject.composeapp.generated.resources.no_data
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource


class IndexScreen : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<IndexScreenModel>()
        val state by screenModel.state.collectAsState()

        Box(modifier = Modifier.fillMaxSize()) {
            when(state) {
                is IndexScreenState.Init -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)) {
                        Text(
                            text = stringResource(Res.string.no_data),
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.size(22.dp))
                        Button(onClick = screenModel::getIndex) {
                            Text(
                                text = stringResource(Res.string.get_pokemon),
                                color = Color.White
                            )
                        }
                    }
                }
                is IndexScreenState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is Success -> {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        contentPadding = PaddingValues(10.dp, 10.dp)
                    ) {
                        items(state.pokemonList) { pokemonEntity ->
                            PokemonCard(pokemonEntity)
                        }
                    }
                }
            }
        }
    }
}
