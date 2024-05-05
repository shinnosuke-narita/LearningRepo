package com.example.project.presentation.index

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.project.entities.PokemonEntity
import com.example.project.presentation.index.IndexScreenState.Success
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
                    val data: List<PokemonEntity> = (state as? Success)?.pokemonList ?: emptyList()
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp)
                    ) {
                        items(data) { pokemon ->
                            Text(
                                pokemon.name,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
